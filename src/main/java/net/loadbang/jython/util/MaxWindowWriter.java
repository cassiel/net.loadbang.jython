//	$Id$
//	$Source$

package net.loadbang.jython.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**	A class mapping Jython's output printing to post()/error() messages in
 	the Max window. Since Jython 2.5 we've not been able to rely on the
 	default printing (buffered by Jython) working properly.
 
	@author Nick Rothwell, nick@cassiel.com / nick@loadbang.net
 */

abstract public class MaxWindowWriter extends OutputStream {
	private ByteArrayOutputStream itsBufferStream = new ByteArrayOutputStream();
	static private String theLineSep = System.getProperty("line.separator");

	@Override
	public void close() throws IOException { }

	@Override
	public void flush() throws IOException { }
	
	abstract void printToMaxWindow(String message);

	/** A rather inefficient byte writer; we check for line separation
	 	on every character! TODO: apply a heuristic to cut this down.
	 */

	@Override
	public void write(int b) throws IOException {
		itsBufferStream.write(b);
		attemptFlushes();
	}

	/**	Not a very efficient buffer divider, but for printing to the Max
	 	window I don't think it matters much - let's go for clarity.
	 */

	private void attemptFlushes() {
		String s = itsBufferStream.toString();
		byte[] savedContent = itsBufferStream.toByteArray();

		int pos = s.indexOf(theLineSep);
		int discarded = 0;
		
		while (pos >= 0) {
			printToMaxWindow(s.substring(0, pos));
			s = s.substring(pos + theLineSep.length());
			discarded += pos + theLineSep.length();
			pos = s.indexOf(theLineSep);
		}
		
		itsBufferStream.reset();
		itsBufferStream.write(savedContent, discarded, savedContent.length - discarded);
	}
}
