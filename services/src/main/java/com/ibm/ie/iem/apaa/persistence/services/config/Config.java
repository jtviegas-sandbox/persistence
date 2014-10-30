package com.ibm.ie.iem.apaa.persistence.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Bootstrap config class for dataservices, client code must always include
 * this package to be "component-scanned"
 * 
 * @author jtv
 * 
 */
@Configuration
// import data repositories Configuration
@Import({com.ibm.ie.iem.apaa.persistence.repositories.config.Config.class})
@ImportResource({ "classpath*:/services-config.xml" })
@PropertySource({ "classpath:/services.properties" })
@ComponentScan(basePackages = "com.ibm.ie.iem.apaa.persistence.services")
public class Config {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		return new PropertySourcesPlaceholderConfigurer();
	}


}
