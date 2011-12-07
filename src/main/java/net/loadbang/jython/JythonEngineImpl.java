//	$Id$
//	$Source$

package net.loadbang.jython;

import java.io.File;
import java.util.List;
import java.util.Stack;

import net.loadbang.jython.util.Invocation;
import net.loadbang.jython.util.JythonConverters;
import net.loadbang.jython.util.MaxWindowErrorWriter;
import net.loadbang.jython.util.MaxWindowPostWriter;
import net.loadbang.jython.util.StdErrFlusher;
import net.loadbang.scripting.EngineImpl;
import net.loadbang.scripting.MaxObjectProxy;
import net.loadbang.scripting.util.Converters;
import net.loadbang.scripting.util.Manifest;
import net.loadbang.scripting.util.exn.DataException;

import org.python.core.PyDictionary;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import com.cycling74.max.Atom;

/**	An implementation of {@link EngineImpl} for Jython.

	@author Nick Rothwell, nick@cassiel.com / nick@loadbang.net
 */

public class JythonEngineImpl extends EngineImpl {
	/**	A stack of callback functions to call on disposal or "clear". */
	private Stack<PyFunction> itsCleanupCallbacks00 = null;
	
	/**	The dictionary (environment) for this Jython instance. */
	private PyDictionary itsDictionary = new PyDictionary();
	
	/**	The directory for any place-holder. */
	private String itsPlaceHolderDirectory;
	
	/**	The Python interpreter for a place-holder.
	 	We build it on demand, and regenerate it whenever we
	 	need to alter its search path (when the place-holder changes).
	 	
	 	<P>(For "script" calls, we create a transient interpreter.)
	 */

	private PythonInterpreter itsInterpreter00 = null;
	
	/**	This is an interpreter with no additional path information, for "exec" and
		"eval" calls. It is similar
		to the Groovy shell: although this is slightly less functional than having
		eval/exec pick up the place-holder, it does protect us against (for example)
		having "script foo" change the search path for subsequent execs.
	 */

	private PythonInterpreter itsShellInterpreter = createPlainInterpreter();
	
	/** Converters between Atoms and Python objects. */

	private JythonConverters itsConverters;
	
	static {
		PythonInterpreter.initialize(System.getProperties(), null, new String[] { });
		new StdErrFlusher().begin();
	}
	
	/** Constructor: create a Jython scripting engine.

	 	@param proxy a proxy for the enclosing MaxObject
	 */

	public JythonEngineImpl(MaxObjectProxy proxy) {
		super(proxy);
		itsConverters = new JythonConverters();
		clear();
	}
	
	/** Return the converters.
	
		@see net.loadbang.scripting.EngineImpl#getConverters()
	 */

	@Override
	protected Converters getConverters() {
		return itsConverters;
	}
	
	/**	Evaluate a Python expression, outputting the value to the
	 	leftmost outlet.
	 	
	 	<p>The Jython interpreter instance has no
	 	access to modules in Max's search path. (We might change
	 	this in future releases.)
	 	
	 	@param expr the Python expression

	 	@see net.loadbang.scripting.EngineImpl#eval(java.lang.String)
	 */
	@Override
	public void eval(final String expr) {
		new Invocation() { @Override public void invoke() {
			getProxy().outlet(0, itsShellInterpreter.eval(expr));
		}}.protect();
	}

	/** Execute a Python statement.
	
		<p>The Jython interpreter instance has no
	 	access to modules in Max's search path. (We might change
	 	this in future releases.)

	 	@see net.loadbang.scripting.EngineImpl#exec(java.lang.String)
	 */

	@Override
	public void exec(final String statement) {
		new Invocation() { @Override public void invoke() {
			itsShellInterpreter.exec(statement);
		}}.protect();
	}
	
	/**	Invoke a named function in Python: called from the MXJ world by a
	 	message from Max.
	 	
	 	@param fn the name of the function
	 	@param inlet00 the inlet (if there are several); null if there is
	 	only one inlet
	 	@param args the arguments - the rest of the Max message
	 	
	 	@see net.loadbang.scripting.EngineImpl#invoke(java.lang.String, java.lang.Integer, com.cycling74.max.Atom[])
	 */

	@Override
	public void invoke(final String fn, final Integer inlet00, final Atom[] args) {
		//	Since we lookup items via the interpreter, not the dictionary, we
		//	need an interpreter. We just use the shell interpreter (which is
		//	permanently installed) - everything works from the same dictionary
		//	anyway.
		
		new Invocation() { @Override public void invoke() {
			PyObject f00 = itsShellInterpreter.get(fn);
			
			if (f00 == null) {
				getProxy().error("function not found: " + fn);
			} else if (!(f00 instanceof PyFunction)) {
				getProxy().error("not a function: " + fn + " [" + f00.getClass() + "]");
			} else {
				Atom[] objArray = args;
				
				if (inlet00 != null) {
					objArray = prepend(Atom.newAtom(inlet00), args);
				}
	
				try {
					List<PyObject> pyList = itsConverters.atomsToPyObjects(objArray, 0);
					f00.__call__(pyList.toArray(new PyObject[] { }));
				} catch (DataException e) {
					getProxy().error("cannot convert from Atoms: " + e);
				}
			}
		}}.protect();
	}
	
	/**	Unwind the callback stack, clear the environment and reinstantiate
	 	the engine and maxObject bindings.
	 	
	 	@see net.loadbang.scripting.Engine#clear()
	 */

	public void clear() {
		unwindCallbacks();
		
		itsDictionary.clear();
	
		itsShellInterpreter.set(Manifest.Vars.MAX_OBJECT, getProxy());
		itsShellInterpreter.set(Manifest.Vars.ENGINE, this);
	}

	/**	Cleanup machinery, called from Jython. Add a cleanup closure. */
	
	public void addCleanup(Object obj) {
		if (obj instanceof PyFunction) {
			if (itsCleanupCallbacks00 == null) {
				itsCleanupCallbacks00 = new Stack<PyFunction>();
			}
	
			itsCleanupCallbacks00.push((PyFunction) obj);
		} else {
			getProxy().error("addCleanup: not a function");
		}
	}
	
	/**	Run the script. We create a scratch interpreter just so that we can set
	 	up the module search path temporarily.
	 */
	
	public void runScript(final String directory, final String filename) {
		final PythonInterpreter interpreter = createPathedInterpreter(directory);
		
		new Invocation() { @Override public void invoke() {
			interpreter.execfile(new File(directory, filename).getPath());
		}}.protect();
	}
	
	/**	Create a lightweight, pathless interpreter for eval() and exec() calls.

	 	@return the interpreter
	 */

	private PythonInterpreter createPlainInterpreter() {
		PythonInterpreter p = new PythonInterpreter(itsDictionary);
		setupConsoleStreams(p);
		return p;
	}

	/**	Create an interpreter which will search in a directory for
	 	modules. We have to add items to sys.path on demand (and we
	 	also have to expose "path" as an identifier).
	 	
	 	<P>NB: the Python system state we create for this interpreter
	 	instance is linked into the thread, so currently we cannot
	 	call through multiple interpreters in one thread.
	 	
	 	@param directory a directory to add to sys.path
	 	@return an interpreter
	 */

	private PythonInterpreter createPathedInterpreter(String directory) {
		//	We create a new PySystemState for this interpreter, to keep "sys.XXX"
		//	private.
		
		PySystemState state = new PySystemState();
		
		state.path.append(new PyString(directory));

		PythonInterpreter p = new PythonInterpreter(itsDictionary, state);
		setupConsoleStreams(p);
		return p;
	}

	/**	Set up stdout and stderr streams to post to Max, rather than relying
	 	on standard Python streams (which don't buffer properly under 2.5).

		@param interp the interpreter
	 */

	private void setupConsoleStreams(PythonInterpreter interp) {
		interp.setOut(new MaxWindowPostWriter(getProxy()));
		interp.setErr(new MaxWindowErrorWriter(getProxy()));
	}

	/**	Unwind the callback functions.

		@see net.loadbang.scripting.EngineImpl#unwindCallbacks()
	 */

	@Override
	public void unwindCallbacks() {
		if (itsCleanupCallbacks00 != null) {
			while (!itsCleanupCallbacks00.empty()) {
				itsCleanupCallbacks00.pop().__call__();
			}
		}
	}

	/**	Retrieve a variable; return null if not found.

		@see net.loadbang.scripting.EngineImpl#getVar00(java.lang.String)
	 */

	@Override
	protected Object getVar00(String id) {
		try {
			return itsShellInterpreter.get(id, Object.class);
		} catch (NullPointerException _) {
			return null;
		}
	}

	/**	Set a binding in the interpreter's environment.
	 	
	 	@param id the name of the identifier
	 	@param args the value to be bound

	 	@see net.loadbang.scripting.EngineImpl#setVar(java.lang.String, java.lang.Object)
	 */

	@Override
	public void setVar(String id, Object args) {
		itsShellInterpreter.set(id, args);
	}
	
	/**	Establish a place-holder directory. Unlike Groovy, which sits on a directory
		in a rather special manner, Jython doesn't care about the directory - it has
		a separate mechanism for homing modules - so the place-holder machinery
		here is local and for convenience only.
	 */
	
	@Override
	public void setupEngineOnPlaceHolder(String directory) {
		itsPlaceHolderDirectory = directory;
		itsInterpreter00 = createPathedInterpreter(directory);
	}
	
	/**	Run a script using an interpreter rooted on a place-holder directory.

	 	@see net.loadbang.scripting.Engine#runUsingPlaceHolder(java.lang.String)
	 */

	public void runUsingPlaceHolder(final String name) {
		if (itsInterpreter00 == null) {
			getProxy().error("engine not loaded: place-holder problem?");
		} else {
			new Invocation() { @Override public void invoke() {
				itsInterpreter00.execfile(new File(itsPlaceHolderDirectory, name).getPath());
			}}.protect();
		}
	}
}
