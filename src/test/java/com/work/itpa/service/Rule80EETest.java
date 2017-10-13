package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.web.rest.util.PersonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class Rule80EETest {

	@Autowired
	ItpaRuleAdminService dService;
	
	String sectionName = "80EE";
	
	@Rule public TestName testName = new TestName();
	

	
	@Test
	public void test80EEInterestPaid() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addPropertyDetails(fPerson, "Pune House", "Pune", FiConstants.OWNERSHIP_OWN, 4000000, 3000000, 40000, true);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);
		
		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName,40000);
		
		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	
	
	}
	
	
	@Test
	public void test80EEInterestPaidAboveLimit() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addPropertyDetails(fPerson, "Pune House", "Pune", FiConstants.OWNERSHIP_OWN, 4000000, 3000000, 60000, true);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);
		
		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName,50000);
		
		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	
	}
}
