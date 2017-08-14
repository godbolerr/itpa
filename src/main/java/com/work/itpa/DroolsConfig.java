package com.work.itpa;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.work.itpa.rules"})
public class DroolsConfig {

	@Bean
    public KieContainer kieContainer() throws IOException {
        KieContainer kContainer = KieServices.Factory.get().getKieClasspathContainer();
        return kContainer;
    }
}