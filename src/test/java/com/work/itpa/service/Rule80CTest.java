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

import com.work.itpa.ItpaApp;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Investment;
import com.work.itpa.utils.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CTest {
	
	
	@Rule public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName = "80C";

	@Test
	public void test80CPPFContribution() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		
		BigDecimal providentFundAmount = new BigDecimal("40000");
		
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		fPerson.addInvestment(new Investment(providentFundAmount,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, providentFundAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, providentFundAmount);

		assertEquals(true, result);
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
	
	@Test
	public void test80CPPFandSCCSContribution() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentialStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		
		BigDecimal providentFundAmount = new BigDecimal("75000");
		
		BigDecimal scssAmount = new BigDecimal("100000");
		
		BigDecimal expectedAmount = new BigDecimal("150000");
		
		fPerson.addInvestment(new Investment(providentFundAmount,"PPF_CONTRIBUTION","Invest in PPF Funds "));
		
		fPerson.addInvestment(new Investment(scssAmount,"SCSS","Invest in SCSS "));
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName, 2);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, providentFundAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName, scssAmount);

		assertTrue(result);
		
		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName, expectedAmount);

		assertEquals(true, result);
		
		
		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);
		

	}	

}
