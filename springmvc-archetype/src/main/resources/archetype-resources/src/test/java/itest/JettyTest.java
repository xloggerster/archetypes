#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.itest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class JettyTest {
	private static Server server;

	@BeforeClass
	public static void startServer() throws Exception {
		WebAppContext context = new WebAppContext("./src/main/webapp", "/");

		server = new Server(9998);
		server.setHandler(context);

		server.setStopAtShutdown(true);
		server.start();

	}

	@AfterClass
	public static void stopServer() throws Exception {
		server.stop();
	}
}
