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
	public void test80DForBachelorMale() {		
		FinPerson fPerson = getBachelorMale();
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		assertEquals(2, result.getMessages().size());
		
		//System.out.println(result);
	}

	@Test
	public void test80DForMarriedMale() {		
		FinPerson fPerson = getMarriedMale();
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		assertEquals(4, result.getMessages().size());
		
		System.out.println(result);
	}

	@Test
	public void test80DForMarriedMaleWithOneDaughter() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		assertEquals(6, result.getMessages().size());
		
		System.out.println(result);
	}
	
	@Test
	public void test80UForMarriedMaleWithOneDaughter() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setDisabilityPercent(43);
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		//assertEquals(6, result.getMessages().size());
		System.out.println(result);
	}
		
	@Test
	public void test80UForMarriedMaleWithOneDaughterForHigherDisability() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setDisabilityPercent(85);
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		//assertEquals(6, result.getMessages().size());
		System.out.println(result);
	}
}
