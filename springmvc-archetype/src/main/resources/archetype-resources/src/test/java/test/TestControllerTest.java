#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${package}.controller.TestController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestControllerTest {

	@Autowired
	private TestController testController;

	@Test
	public void test() {
		Map<String, Object> result = testController.doSomething();
		assertTrue(result.containsKey("today"));
		assertTrue(result.get("today") instanceof Date);
	}

}
