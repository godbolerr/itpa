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
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.SystemFlag;
import com.work.itpa.itparules.ItpaApp;


//TODO Add Summary Section

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class Rule80CCD2Test {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName80ccd2 = "80CCD2";

	@Test
	public void test80CCD2PensionContributionByEmployer() {

		Assessee assessee = PersonUtil.getBachelorMale();
		
		Person self = PersonUtil.getPersonWithRelation(assessee, FiConstants.RELATIONSHIP_SELF);
		self.setAge(20);
		
		SystemFlag sflag = new SystemFlag();
		
		sflag.setHasSalary(Boolean.TRUE);
		
		assessee.setSystemFlag(sflag);

		BigDecimal pensionSchemeCCD2Amount = new BigDecimal("40000");

		assessee.addInvestment(new Investment(pensionSchemeCCD2Amount, "PS_EMPLOYER", "Investment in Pension Scheme by Employer "));

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), sectionName80ccd2);

		assertTrue(result);

		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(), sectionName80ccd2, 1);

		assertTrue(result);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}
}
