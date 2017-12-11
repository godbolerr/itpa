package com.work.itpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Investment;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CCD1BTest {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ccd1b = "80CCD1B";

	@Test
	public void test80CCD1BNPSBelowMax() {

		Assessee assessee = PersonUtil.getBachelorMale();

		BigDecimal nps80ccd1bAmount = new BigDecimal("40000");

		assessee.addInvestment(new Investment(nps80ccd1bAmount, "PF_NPS", "NPS Investments "));

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd1b);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd1b, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd1b, nps80ccd1bAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd1b,
				nps80ccd1bAmount);

		assertEquals(true, result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}
	
	@Test
	public void test80CCD1BNPSEqualToMax() {

		Assessee assessee = PersonUtil.getBachelorMale();

		BigDecimal nps80ccd1bAmount = new BigDecimal("50000");


		assessee.addInvestment(new Investment(nps80ccd1bAmount, "PF_NPS", "NPS Investments "));

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd1b);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd1b, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd1b, nps80ccd1bAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd1b,
				nps80ccd1bAmount);

		assertEquals(true, result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}	
	
	@Test
	public void test80CCD1BNPSGreaterThanMax() {

		Assessee assessee = PersonUtil.getBachelorMale();

		BigDecimal nps80ccd1bAmount = new BigDecimal("50001");
		
		BigDecimal nps80ccd1bAmountEligibleMax = new BigDecimal("50000");


		assessee.addInvestment(new Investment(nps80ccd1bAmount, "PF_NPS", "NPS Investments "));

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd1b);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd1b, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd1b, nps80ccd1bAmount);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80ccd1b,
				nps80ccd1bAmountEligibleMax);

		assertEquals(true, result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}		

}
