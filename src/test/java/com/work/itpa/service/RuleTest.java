package com.work.itpa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.itparules.ItparulesApplication;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItparulesApplication.class)
public class RuleTest extends ItpaBaseTest{
	
	@Autowired
	ItpaService dService;

	@Test
	public void test80DForBachelorMale() {		
		FinPerson fPerson = getBachelorMale();
		fPerson.setAssesseeType("Resident");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		assertEquals(2, result.getMessages().size());
	}

	@Test
	public void test80DForMarriedMale() {		
		FinPerson fPerson = getMarriedMale();
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		//assertEquals(6, result.getMessages().size());
	}

	@Test
	public void test80DForMarriedMaleWithOneDaughter() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		//assertEquals(9, result.getMessages().size());
	}
	
	@Test
	public void test80UForMarriedMaleWithOneDaughter() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setDisabilityPercent(43);
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
		//assertEquals(6, result.getMessages().size());
	}
		
	@Test
	public void test80UForMarriedMaleWithOneDaughterForHigherDisability() {		
		FinPerson fPerson = getMarriedMaleWithOneDaughter();
		fPerson.setDisabilityPercent(85);
		fPerson.setAssesseeType("Individual");
		FinPersonResult result =   dService.calculateBenefits(fPerson );		
	}
}
