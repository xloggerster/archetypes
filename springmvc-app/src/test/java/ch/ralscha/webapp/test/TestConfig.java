package ch.ralscha.webapp.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "ch.ralscha.webapp.controller" }, excludeFilters = @Filter(type = FilterType.ANNOTATION, value = Configuration.class))
public class TestConfig {

}