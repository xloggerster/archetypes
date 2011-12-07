package ch.ralscha.webapp.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "ch.ralscha.webapp.controller" })
public class TestConfig {

}