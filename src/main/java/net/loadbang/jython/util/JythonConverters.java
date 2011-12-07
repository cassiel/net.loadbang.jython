package net.loadbang.jython.util;

import java.util.ArrayList;
import java.util.List;

import net.loadbang.scripting.util.Converters;
import net.loadbang.scripting.util.exn.DataException;

import org.python.core.PyFloat;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;

import com.cycling74.max.Atom;

public class JythonConverters extends Converters {
	private PyObject pyObjectify(Atom a) throws DataException {
		if (a.isInt()) {
			return new PyInteger(a.getInt());
		} else if (a.isFloat()) {
			return new PyFloat(a.getFloat());
		} else if (a.isString()) {
			return new PyString(a.getString());
		} else {
			throw new DataException("pyObjectify: " + a);
		}
	}

	/**	Convert an array of Atoms into a list of Python objects.

		@param args the Atoms
	 	@param start the start position in the Atom array
	 	@return a list of Python objects
	 	@throws DataException if the Atom cannot be easily represented as a Python object
	 */

	public List<PyObject> atomsToPyObjects(Atom[] args, int start) throws DataException {
		List<PyObject> result = new ArrayList<PyObject>(args.length - start);
		
		for (int i = start; i < args.length; i++) {
			result.add(pyObjectify(args[i]));
		}
		
		return result;
	}
}
