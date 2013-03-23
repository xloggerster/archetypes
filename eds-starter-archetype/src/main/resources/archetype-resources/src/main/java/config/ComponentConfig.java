#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "ch.ralscha.extdirectspring", "${package}.config", "${package}.schedule",
		"${package}.security", "${package}.service", "${package}.web" })
@PropertySource({ "version.properties" })
public class ComponentConfig {
	// nothing here
}