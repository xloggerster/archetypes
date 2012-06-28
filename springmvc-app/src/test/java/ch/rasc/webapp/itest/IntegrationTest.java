package ch.rasc.webapp.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import ch.ralscha.embeddedtc.TomcatTest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class IntegrationTest extends TomcatTest {

	@Test
	public void testCounterPage() throws Exception {
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage("http://localhost:9998");

		assertEquals("Congratulations! Spring is running!", page.getTitleText());

		page = webClient.getPage("http://localhost:9998/?locale=de_de");
		assertEquals("Glückwünsche! Spring läuft!", page.getTitleText());

		page = webClient.getPage("http://localhost:9998/myController/doSomething");
		assertEquals("Login Page", page.getTitleText());

		page = login(page, "wrong", "wrong");
		assertEquals("Login Page", page.getTitleText());
		assertTrue(page.asText().contains("Your login attempt was not successful, try again."));
		assertTrue(page.asText().contains("Reason: Bad credentials"));

		page = login(page, "jimi", "jimispassword");
		assertEquals("Response", page.getTitleText());
		assertEquals("jimi", page.getElementById("me").asText());

	}

	private HtmlPage login(final HtmlPage page, final String userName, final String password) throws IOException {
		HtmlForm form = page.getFormByName("f");
		HtmlSubmitInput submitButton = form.getInputByName("submit");
		form.getInputByName("j_username").setValueAttribute(userName);
		form.getInputByName("j_password").setValueAttribute(password);
		return submitButton.click();
	}
}
