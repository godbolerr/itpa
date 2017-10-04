package com.work.itpa.web.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.service.ItpaService;

import io.github.jhipster.config.JHipsterProperties;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api/itpa")
public class ItpaRuleResource {

	private final Environment env;

	private final JHipsterProperties jHipsterProperties;
	
	@Autowired
	ItpaService itpaService;

	public ItpaRuleResource(Environment env, JHipsterProperties jHipsterProperties) {
		this.env = env;
		this.jHipsterProperties = jHipsterProperties;
	}

	@GetMapping("/health")
	public String getHealth() {

		return "Time is " + LocalDate.now();
	}
	
	
	@RequestMapping(value = "/benefits", method = { RequestMethod.POST })
	public FinPersonResult getBenefits(@RequestBody com.work.itpa.rules.FinPerson fPerson) {
		return itpaService.calculateBenefits(fPerson);
	}	
	
	
	@RequestMapping(value = "/rule", method = { RequestMethod.POST })
	public String updateRuleData(@RequestBody String data) {
		return itpaService.updateRuleData(data);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
