//	$Id$
//	$Source$

package net.loadbang.jython.util;

import net.loadbang.scripting.MaxObjectProxy;

public class MaxWindowPostWriter extends MaxWindowWriter {
	private MaxObjectProxy itsProxy;

	public MaxWindowPostWriter(MaxObjectProxy proxy) {
		itsProxy = proxy;
	}

	@Override
	void printToMaxWindow(String message) {
		itsProxy.post(message);
	}
}
