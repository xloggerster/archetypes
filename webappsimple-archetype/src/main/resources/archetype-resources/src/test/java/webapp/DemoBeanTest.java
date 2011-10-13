#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webapp;

import org.junit.Test;
import ${package}.webapp.DemoBean;
import junit.framework.TestCase;

public class DemoBeanTest extends TestCase {

	@Test
	public void testCounter() {
		DemoBean db = new DemoBean();
		assertEquals(0, db.getCounter());

		db.inc();
		assertEquals(1, db.getCounter());

		db.inc();
		assertEquals(2, db.getCounter());
	}

}
