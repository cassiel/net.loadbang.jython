//	$Id$
//	$Source$

package net.loadbang.jython.util;

import net.loadbang.scripting.MaxObjectProxy;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class MaxWindowPostWriterTest {
	private Mockery itsContext = new JUnit4Mockery();

	@Test
	public void printsOneLine() throws Exception {
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		MaxWindowWriter writer = new MaxWindowPostWriter(proxy);

		itsContext.checking(new Expectations() {{
			one(proxy).post("Hello World");
		}});
		
		writer.write("Hello World".getBytes());
		writer.write(Character.toString('\n').getBytes());
	}
	
	@Test
	public void separatesMultipleLines() throws Exception {
		final Sequence seq = itsContext.sequence("separatesMultipleLines");
		final MaxObjectProxy proxy = itsContext.mock(MaxObjectProxy.class);
		MaxWindowWriter writer = new MaxWindowPostWriter(proxy);

		itsContext.checking(new Expectations() {{
			one(proxy).post("A"); inSequence(seq);
			one(proxy).post("B"); inSequence(seq);
		}});
		
		writer.write("A\nB\n".getBytes());
	}
}
