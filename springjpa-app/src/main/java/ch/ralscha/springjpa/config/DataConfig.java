package ch.ralscha.springjpa.config;

import java.util.Map;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.digest.StringDigester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.common.collect.Maps;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "ch.ralscha.springjpa" })
@ImportResource("classpath:ch/ralscha/springjpa/config/data.xml")
public class DataConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		emf.setPersistenceProvider(new org.hibernate.ejb.HibernatePersistence());

		Map<String, String> properties = Maps.newHashMap();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");

		properties.put("hibernate.connection.driver_class", "org.h2.Driver");
		properties.put("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE");
		properties.put("hibernate.connection.username", "sa");
		properties.put("hibernate.connection.password", "update");

		emf.setJpaPropertyMap(properties);

		emf.setPackagesToScan("ch.ralscha.springjpa.entity");

		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}

	@Bean
	public StringDigester passwordDigester() {
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-256");
		digester.setIterations(100000);
		digester.setSaltSizeBytes(16);
		return digester;
	}

}
