#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
import ${groupId}.embeddedtc.EmbeddedTomcat;

public class StartTomcat {
	public static void main(String[] args) throws Exception {
		EmbeddedTomcat et = new EmbeddedTomcat();
		et.startAndWait();
	}
}