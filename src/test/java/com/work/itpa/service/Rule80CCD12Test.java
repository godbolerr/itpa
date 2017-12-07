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

import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;
import com.work.itpa.utils.PersonUtil;


//TODO Add Summary Section

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CCD12Test {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ccd11 = "80CCD1";

	@Test
	public void test80CCD1PensionSchemeInvestmentEmployeeSelf() {

		FinPerson fPerson = PersonUtil.getBachelorMale();
		Person self = PersonUtil.getPersonWithRelation(fPerson, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);		
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.FALSE);
		
		fPerson.setSystemFlag(sflag);
		
		BigDecimal pensionSchemeCCD1Amount = new BigDecimal("40000");
		
		BigDecimal grossTotalIncome = new BigDecimal("500000");
		
		fPerson.addIncome(new Income(grossTotalIncome,Income.Type.GROSS_TOTAL,Income.Source.NA,"Gross Total Income"));
		
		fPerson.addInvestment(new Investment(pensionSchemeCCD1Amount, "PS_SELF", "Investment in Pension Scheme by Self employed person "));

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd11);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd11, 1);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80ccd11, pensionSchemeCCD1Amount);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}
}
