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
import com.work.itpa.rules.ItpaRule;
import com.work.itpa.rules.RuleTemplate;
import com.work.itpa.service.ItpaRuleService;
import com.work.itpa.service.ItpaService;
import com.work.itpa.web.rest.util.PersonUtil;

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

	@Autowired
	ItpaRuleService itpaRuleService;
	
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

	@RequestMapping(value = "/template", method = { RequestMethod.POST })
	public String updateRuleTemplate(@RequestBody RuleTemplate data) {
		return itpaService.updateRuleTemplate(data);
	}
	
	@RequestMapping(value = "/rule", method = { RequestMethod.POST })
	public String updateRule(@RequestBody ItpaRule rule) {
		return itpaService.updateRule(rule);
	}
	
	@RequestMapping(value = "/itpaBenefits", method = { RequestMethod.POST })
	public FinPersonResult getItpaBenefits(@RequestBody com.work.itpa.rules.FinPerson fPerson) {
		return itpaRuleService.calculateBenefits(fPerson);
	}	
	
	
	@RequestMapping(value = "/itpaBenefitsTest", method = { RequestMethod.POST })
	public FinPersonResult getItpaBenefitsTest(@RequestBody com.work.itpa.rules.FinPerson fPerson) {
		return itpaRuleService.calculateBenefits(PersonUtil.getMarriedMale());
	}	
		
}
