package com.work.itpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.DroolsConfig;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DroolsConfig.class)
public class RuleServiceTest extends ItpaBaseTest{
	
	@Autowired
	RuleService dService;

	@Test
	public void testCalculateBenefit() {		
		FinPerson fPerson = getPerson();
		fPerson.setAge(40);
		fPerson.setDisabilityPercent(20);
		fPerson.setAssesseeType("Individual");
		fPerson.setRelationShipCode("Self");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		assertEquals(2, result.getMessages().size());
		
		System.out.println(result);
	}

}
