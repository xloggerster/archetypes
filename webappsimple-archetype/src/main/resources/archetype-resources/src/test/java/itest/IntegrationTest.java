#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class IntegrationTest extends JettyTest {

	@Test
	public void testCounterPage() throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage("http://localhost:9998");
		assertEquals("webapp", page.getTitleText());
		assertTrue(page.asText().contains("No: 1"));

		page = webClient.getPage("http://localhost:9998");
		assertTrue(page.asText().contains("No: 2"));

		webClient = new WebClient();
		page = webClient.getPage("http://localhost:9998");
		assertTrue(page.asText().contains("No: 1"));
	}
}
