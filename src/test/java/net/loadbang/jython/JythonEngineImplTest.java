//	$Id$
//	$Source$

package net.loadbang.jython;

import java.util.Arrays;
import java.util.List;

import net.loadbang.scripting.Engine;
import net.loadbang.scripting.EngineImpl;
import net.loadbang.scripting.MaxObjectProxy;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cycling74.max.Atom;

@RunWith(JMock.class)
public class JythonEngineImplTest {
	private Mockery itsContext = new JUnit4Mockery();
	
	@Test
	public void canSetAndRetrieveListVariable() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList(1, 2));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.setVariable(new Atom[] { Atom.newAtom("X"), Atom.newAtom(1), Atom.newAtom(2) });
		engine.getVariable("X");
	}
	
	@Test
	public void canExecuteOneLinerToBindVariable() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList((Object) 5, "X", 7));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("X = [5, \"X\", 7]");
		
		engine.getVariable("X");
	}
	
	@Test
	public void canEvaluateToAResult() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, Arrays.asList((Object) 5, "X", 7, "A"));
		}});

		Engine engine = new JythonEngineImpl(proxy);

		engine.exec("x = [5, \"X\", 7]");
		engine.exec("x.append(\"A\")");
		engine.eval("x");
	}
	
	@Test
	public void willTreatNoneAsUndefined() {
		//	This is actually an anti-test! We cannot differentiate None/null from
		//	undefined as far as I can tell. (To be investigated.)

		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).error("no such variable: X");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("X = None");
		
		engine.getVariable("X");
	}
	
	@Test
	public void bindsMaxObject() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, "hello-world");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, 'hello-world')");
	}
	
	@Test
	public void convertsJythonListForOutput() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			//	Cascaded call to the MaxObject outlet, once the list has been converted:
			one(proxy).outlet(with(any(int.class)), with(any(List.class)));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, [5, 'X', 7])");
	}
	
	//	We can't really test what comes out when writing "0.0" in Groovy, since we don't
	//	know the scale of the BigDecimal. (The real problem is that our test coverage isn't
	//	complete - we want to test the Atom conversion.)
	
	@Test
	public void outputsNumericList() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		
		itsContext.checking(new Expectations() {{
			//	Cascaded call to the MaxObject outlet, once the list has been converted:
			one(proxy).outlet(0, Arrays.asList((Object) 1, (Integer) 1000, (Double) 0.0));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("maxObject.outlet(0, [1, 1000, 0.0])");
	}
	
	@Test
	public void mapsMessageToClosureCall() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, "Hello Dolly");
			//one(proxy).outlet(with(any(int.class)), with(any(String.class)));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def doit(_, who): maxObject.outlet(0, 'Hello ' + who)");
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom("Dolly") });
	}
	
	@Test
	public void canInvokeFunctionWithSingleArg() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, -100);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def doit(x): maxObject.outlet(0, -x)");
		engine.invoke("doit", null, new Atom[] { Atom.newAtom(100) });
	}
	
	@Test
	public void canInvokeFunctionWithList() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 3);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def doit(*L): maxObject.outlet(0, len(L))");
		engine.invoke("doit", null, new Atom[] { Atom.newAtom(1), Atom.newAtom(2), Atom.newAtom(3) });
	}
	
	@Test
	public void canInvokeFunctionWithInletAndList() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 30);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def doit(x, *L): maxObject.outlet(0, x * len(L))");
		engine.invoke("doit", 10, new Atom[] { Atom.newAtom(1), Atom.newAtom(2), Atom.newAtom(3) });
	}
	
	@Test
	public void survivesInvocationOfUnknownFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("function not found: doit");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom(1), Atom.newAtom(22), Atom.newAtom(3) });
	}
	
	@Test
	public void survivesInvocationOfNonFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("not a function: doit [class org.python.core.PyInteger]");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("doit = 456");
		
		engine.invoke("doit", 0, new Atom[] { Atom.newAtom(1), Atom.newAtom(22), Atom.newAtom(3) });
	}
	
	@Test
	public void canInvokeFunctionWithMultipleArgs() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 6);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def doit(i, j, k): maxObject.outlet(0, i + j + k)");
		engine.invoke("doit", null, new Atom[] { Atom.newAtom(1), Atom.newAtom(2), Atom.newAtom(3) });
	}
	
	@Test
	public void actuallyDoesClear() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("no such variable: X");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.setVariable(new Atom[] { Atom.newAtom("X"), Atom.newAtom(1), Atom.newAtom(2) });
		engine.clear();
		engine.getVariable("X");
	}
	
	@Test
	public void callsCleanupCallback() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("def foo(): maxObject.outlet(0, 999)");
		engine.exec("engine.addCleanup(foo)");
		engine.exec("engine.clear()");
	}
	
	@Test
	public void survivesCleanupWithNonFunction() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("addCleanup: not a function");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("engine.addCleanup(1234)");
		engine.exec("engine.clear()");
	}
	
	@Test
	public void execCanBindVariables() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.exec("x = 999");
		engine.getVariable("x");
	}
	
	@Test
	public void canRunScriptGivenDirectory() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			ignoring(proxy).post(with(any(String.class)));
			one(proxy).outlet(0, 999);
		}});

		Engine engine = new JythonEngineImpl(proxy);

		engine.runScript("src/test/resources/test-data/script", "hello.py");
	}
	
	@Test
	public void canRunScriptUsingPlaceHolder() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			ignoring(proxy).post(with(any(String.class)));
			one(proxy).outlet(0, 2001);
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.setupEngineOnPlaceHolder("src/test/resources/test-data/place");
		engine.runUsingPlaceHolder("myscript.py");
	}
	
	@Test
	public void placeHolderReloadClearsPath() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			ignoring(proxy).post(with(any(String.class)));
			one(proxy).outlet(0, "OK");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.setupEngineOnPlaceHolder("src/test/resources/test-data/place-a");
		engine.setupEngineOnPlaceHolder("src/test/resources/test-data/place-b");

		engine.runUsingPlaceHolder("lookForA.py");
	}
	
	@Test
	public void reportsWhenNoPlaceHolder() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error("engine not loaded: place-holder problem?");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.runUsingPlaceHolder("foo.py");
	}
	
	interface MaxObjectProxy2 extends MaxObjectProxy { }
	
	@Test
	public void canCallThroughTwoEngines() {
		final MaxObjectProxy proxy1 = itsContext.mock(MaxObjectProxy.class);
		final MaxObjectProxy proxy2 = itsContext.mock(MaxObjectProxy2.class);

		itsContext.checking(new Expectations() {{
			one(proxy2).post("thread test OK");
		}});
		
		EngineImpl engine1 = new JythonEngineImpl(proxy1);
		Engine engine2 = new JythonEngineImpl(proxy2);
		
		engine1.setVar("engine2", engine2);
		engine1.runScript("src/test/resources/test-data/script", "thread-test-1.py");
	}
	
	@Test
	public void systemSurvivesPythonError() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
		}});

		Engine engine = new JythonEngineImpl(proxy);
		
		engine.runScript("src/test/resources/test-data/script", "faulty.py");
	}
	
	@Test
	public void pythonMapsStdOutToMaxPost() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).post("HELLO WORLD");
		}});

		Engine engine = new JythonEngineImpl(proxy);
		engine.exec("print 'HELLO WORLD'");
	}
	
	@Ignore
	public void pythonMapsStdErrToMaxError() {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);

		itsContext.checking(new Expectations() {{
			one(proxy).error(with(any(String.class)));
		}});

		Engine engine = new JythonEngineImpl(proxy);
		engine.exec("]]]syntax error");
	}
}
