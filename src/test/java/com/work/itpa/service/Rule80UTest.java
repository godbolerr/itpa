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
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;


// TODO Do we really test for zero deduction ?

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80UTest {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80u = "80U";

	@Test
	public void testDisabilitySelfFourtyToSeventyNinePercent() {

		BigDecimal disabilitySelfDeduction = new BigDecimal("75000");

		Assessee fPerson = PersonUtil.getBachelorMale();

		SystemFlag sflag = new SystemFlag();

		sflag.setHasSalary(Boolean.TRUE);
		sflag.setHasDisabilitySelf(Boolean.TRUE);
		
		Disability disability = fPerson.getDisablity();
		
		disability.setPercentSelf("40_TO_79");

		fPerson.setSystemFlag(sflag);

		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		Assessment finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80u);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80u, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80u, disabilitySelfDeduction);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80u,
				disabilitySelfDeduction);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
}
