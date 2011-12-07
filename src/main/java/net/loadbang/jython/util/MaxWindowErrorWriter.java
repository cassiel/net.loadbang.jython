//	$Id$
//	$Source$

package net.loadbang.jython.util;

import net.loadbang.scripting.MaxObjectProxy;

public class MaxWindowErrorWriter extends MaxWindowWriter {
	private MaxObjectProxy itsProxy;

	public MaxWindowErrorWriter(MaxObjectProxy proxy) {
		itsProxy = proxy;
	}

	@Override
	void printToMaxWindow(String message) {
		itsProxy.error(message);
	}
}
