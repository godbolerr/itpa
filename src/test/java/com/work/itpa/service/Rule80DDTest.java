package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.domain.Disability;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;


// TODO Do we really test for zero deduction ?

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80DDTest {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80dd = "80DD";

	@Test
	public void testDisabilityDependentFourtyToSeventyNinePercent() {

		BigDecimal disabilityDependentDeduction = new BigDecimal("75000");

		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);		

		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasDisabledDependent(Boolean.TRUE);
		
		Disability disability = fPerson.getDisablity();
		
		disability.setDependentDisabilityPercent("40_TO_79");

		fPerson.setSystemFlag(sflag);



		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80dd);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80dd, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80dd, disabilityDependentDeduction);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80dd,
				disabilityDependentDeduction);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
}
