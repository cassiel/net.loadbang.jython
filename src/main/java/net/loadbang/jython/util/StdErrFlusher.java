//	$Id$
//	$Source$

package net.loadbang.jython.util;

/**	Horrible hack to get stderr flushed periodically so that we see Jython's
 	Java-level errors (including Python compilation errors).
 	
	@author Nick Rothwell, nick@cassiel.com / nick@loadbang.net
 */

public class StdErrFlusher implements Runnable {
	public void begin() {
		new Thread(this).start();
	}

	public void run() {
		while (true) {
			System.err.flush();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
