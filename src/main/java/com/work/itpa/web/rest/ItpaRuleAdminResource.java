package com.work.itpa.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.work.itpa.domain.RuleTemplate;
import com.work.itpa.service.ItpaRuleAdminService;
import com.work.itpa.service.ItpaRuleService;
import com.work.itpa.web.rest.util.HeaderUtil;
import com.work.itpa.web.rest.util.PaginationUtil;

import io.github.jhipster.config.JHipsterProperties;
import io.swagger.annotations.ApiParam;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api/itpa")
public class ItpaRuleAdminResource {

	private final Environment env;

	private final JHipsterProperties jHipsterProperties;
	
	private static final String RULE_TEMPLATE_ENTITY_NAME = "ruleTemplate";

	@Autowired
	ItpaRuleAdminService itpaRuleAdminService;

	@Autowired
	ItpaRuleService itpaRuleService;
	
	public ItpaRuleAdminResource(Environment env, JHipsterProperties jHipsterProperties) {
		this.env = env;
		this.jHipsterProperties = jHipsterProperties;
	}
	
	@PostMapping("/ruleTemplates")
	public ResponseEntity<RuleTemplate> saveTemplate(@RequestBody RuleTemplate ruleTemplate) throws URISyntaxException  {
		RuleTemplate result =  itpaRuleAdminService.save(ruleTemplate);
        return ResponseEntity.created(new URI("/api/itpa/ruleTemplates/" + ruleTemplate.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(RULE_TEMPLATE_ENTITY_NAME, ruleTemplate.getId().toString()))
                .body(result);
	}
	
	
    @GetMapping("/ruleTemplates")
    @Timed
    public ResponseEntity<List<RuleTemplate>> getAllTemplates(@ApiParam Pageable pageable) {
        Page<RuleTemplate> page = itpaRuleAdminService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itpa/ruleTemplates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }	
			
}
