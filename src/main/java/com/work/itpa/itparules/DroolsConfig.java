package com.work.itpa.itparules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.work.itpa.domain.ItpaConfig;

/**
 * Drools specific bean to take care of rule execution.
 * 
 * @author Developer
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.work" })
public class DroolsConfig {

	public static final String PROPS_FILE = "itpaconfig.properties";

	@Bean
	public KieContainer kieContainer() throws IOException {
		return KieServices.Factory.get().getKieClasspathContainer();
	}

	/**
	 * TODO This can also be loaded from Spring application properties configuration.
	 * @return
	 */
	@Bean
	public ItpaConfig loadConfiguration() {
		ItpaConfig config = new ItpaConfig();

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = DroolsConfig.class.getClassLoader().getResourceAsStream(PROPS_FILE);
			if (input == null) {
				System.out.println("Sorry, unable to find " + PROPS_FILE);
				return config;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			config.setProps(prop);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return config;
	}
}