package ch.ralscha.webapp;

import org.junit.Test;
import ch.ralscha.webapp.DemoBean;
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
