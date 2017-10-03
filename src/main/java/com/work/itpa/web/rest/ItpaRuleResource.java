package com.work.itpa.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.config.JHipsterProperties;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api/itpa")
public class ItpaRuleResource {

	private final Environment env;

	private final JHipsterProperties jHipsterProperties;

	public ItpaRuleResource(Environment env, JHipsterProperties jHipsterProperties) {
		this.env = env;
		this.jHipsterProperties = jHipsterProperties;
	}

	@GetMapping("/health")
	public String getHealth() {

		return "Time is " + LocalDate.now();
	}
}
