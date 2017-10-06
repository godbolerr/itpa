package com.work.itpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

@Configuration
public class JacksonConfiguration {

	/*
	 * Jackson Afterburner module to speed up serialization/deserialization.
	 */
	@Bean
	public AfterburnerModule afterburnerModule() {
		return new AfterburnerModule();
	}

	@Autowired(required = true)
	public void configeJackson(ObjectMapper jackson2ObjectMapper) {

		jackson2ObjectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

	}

}
