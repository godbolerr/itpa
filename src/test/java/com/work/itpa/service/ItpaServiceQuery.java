package com.work.itpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.web.rest.util.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class ItpaServiceQuery {

	
	@Autowired
	ItpaRuleService dService;
	
	/**
	 * 	public String id;
	public int assessmentYear;
	public String ruleTemplate;
	public String status;
	public String name;
	public String section;
	public String residentStatus;
	public String assesseeType;
	public String relationshipCode;
	public String deductionType;
	public int minAge;
	public int maxAge;
	public int maxDeduction;
	 */
	@Test
	public void test80D0() {
//		List<RuleData> data = dService.getDecisionData(2017, "80D_0");
//		
//		assertTrue(data.size() > 0);
		
	}
	
	@Test
	public void getRules(){
		

		FinPersonResult result = dService.calculateBenefits(PersonUtil.getMarriedMale());
		
		result = dService.calculateBenefits(PersonUtil.getMarriedMale());
		
		result = dService.calculateBenefits(PersonUtil.getMarriedMale());
		
	}
	
	
	@Test
	public void getRuleTemplates(){
		
//		List<RuleTemplate> templates = dService.getRuleTemplates(2017);
//		
//		for (RuleTemplate ruleTemplate : templates) {
//			System.out.println(ruleTemplate.getRuleText());
//		}
	}	

}
