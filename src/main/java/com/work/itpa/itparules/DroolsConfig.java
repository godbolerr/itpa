package com.work.itpa.itparules;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Drools specific bean to take care of rule execution.
 * 
 * @author Developer
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.work" })
public class DroolsConfig {

	@Bean
	public KieContainer kieContainer() throws IOException {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
}