package ch.rasc.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "ch.rasc.webapp" })
public class ComponentConfig {
	// nothing here
}