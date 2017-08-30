package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.itparules.ItparulesApplication;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItparulesApplication.class)
public class Rule80UTest {

	@Autowired
	ItpaService dService;

	String sectionName = "80U";
	
	@Rule public TestName testName = new TestName();

	@Test
	public void test80USelfResidentIndividualFiftyPercentDisability() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setDisabilityPercent(50);
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 75000);

		assertTrue(result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);



	}

	@Test
	public void test80USelfResidentIndividualEightyPercentDisability() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setDisabilityPercent(81);
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), sectionName, 125000);

		assertTrue(result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);


		
	}

}
