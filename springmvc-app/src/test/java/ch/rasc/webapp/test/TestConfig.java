package ch.rasc.webapp.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "ch.rasc.webapp.controller" })
public class TestConfig {
	//nothing here
}