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

import com.work.itpa.domain.Donation;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.Assessee;
import com.work.itpa.domain.Assessment;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.itparules.ItpaApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpaApp.class)
public class TestAllSections {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	@Test
	public void testAllSections() {

		String section80cName = "80C";

		String sectionName80g = "80G";

		String section80TTAName = "80TTA";

		String section80RRBName = "80RRB";

		String ggcSectionName = "80GGC";

		String sectionNamegga = "80GGA";

		BigDecimal incomeInterest = new BigDecimal("11000.00");

		BigDecimal expected80TTA = new BigDecimal("10000.00");

		String summarySectionQQBRRBName = "80RRB";

		BigDecimal incomerrb = new BigDecimal("202000.14");

		BigDecimal income2rrb = new BigDecimal("322000.13");

		BigDecimal totalIncomeRrb = new BigDecimal("300000.00");

		BigDecimal donation1ggc = new BigDecimal("20000.00");
		BigDecimal donation2ggc = new BigDecimal("35000.10");

		Assessee assessee = PersonUtil.getBachelorMale();

		BigDecimal providentFundAmount = new BigDecimal("75000");

		BigDecimal scssAmount = new BigDecimal("100000");

		BigDecimal expectedAmount = new BigDecimal("150000");
		

		BigDecimal donationgga = new BigDecimal("20000.00");

		PersonUtil.addDonation(assessee, donationgga, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for biology");

		BigDecimal donation2gga = new BigDecimal("30000.00");

		PersonUtil.addDonation(assessee, donation2gga, Donation.Type.SCIENTIFIC,
				"Donation to scientific research for physics");

		BigDecimal donation180g = new BigDecimal("20000.00");
		BigDecimal donation280g = new BigDecimal("14000.00");

		BigDecimal expectedExemption80g = new BigDecimal("24000.00");

		PersonUtil.addDonation(assessee, donation180g, Donation.Type.OTHER, "PM_DROUGHT_RELF_FND",
				"Donation PM_DROUGHT_RELF_FND. 50% exemption expected.");
		PersonUtil.addDonation(assessee, donation280g, Donation.Type.OTHER, "NAT_DEF_FUND_CEN_GOVT",
				"Donation NAT_DEF_FUND_CEN_GOVT");

		assessee.addInvestment(new Investment(providentFundAmount, "PPF_CONTRIBUTION", "Invest in PPF Funds "));

		assessee.addInvestment(new Investment(scssAmount, "SCSS", "Invest in SCSS "));

		PersonUtil.addIncome(assessee, incomeInterest, Income.Type.INTEREST, Income.Source.NA,
				"Interest from savings bank ");

		PersonUtil.addIncome(assessee, incomerrb, Income.Type.ROYALTY, Income.Source.PATENT, "Income from Patent 1");

		PersonUtil.addIncome(assessee, income2rrb, Income.Type.ROYALTY, Income.Source.PATENT, "Income from Patent 2 ");

		PersonUtil.addDonation(assessee, donation1ggc, Donation.Type.POLITICAL, "Donation to policical party xyz ");

		PersonUtil.addDonation(assessee, donation2ggc, Donation.Type.POLITICAL, "Donation to policical party abc ");

		Assessment finResult = dService.calculateBenefits(assessee);

		boolean result = PersonUtil.hasSection(finResult.getDeductions(), section80cName);

		// assertTrue(result);
		//
		// result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),
		// section80cName, 2);
		//
		// assertTrue(result);
		//
		// result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(),
		// section80cName, providentFundAmount);
		//
		// assertTrue(result);
		//
		// result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(),
		// section80cName, scssAmount);
		//
		// assertTrue(result);
		//
		// result =
		// PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(),
		// section80cName,
		// expectedAmount);
		//
		// assertEquals(true, result);

		// Verify section and amount deducted

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80RRBName, incomerrb);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80RRBName, income2rrb);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(),
				summarySectionQQBRRBName, totalIncomeRrb);

		assertTrue(totalResult);

		// Verify section and amount deducted

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), section80TTAName, incomeInterest);

		assertEquals(true, result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), section80TTAName,
				expected80TTA);

		assertEquals(true, result);

		// Verify section and amount deducted

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), ggcSectionName, donation1ggc);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), ggcSectionName, donation2ggc);

		assertTrue(result);

		// Verify final deduction which is applicable.

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), ggcSectionName,
				donation1ggc.add(donation2ggc));

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionNamegga, donationgga);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionNamegga, donation2gga);

		assertTrue(result);

		result = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionNamegga,
				donationgga.add(donation2gga));

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donation180g);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), sectionName80g, donation280g);

		assertTrue(result);

		totalResult = PersonUtil.hasSummarySectionWithAmount(finResult.getSummaryDeductions(), sectionName80g,
				expectedExemption80g);

		assertTrue(totalResult);

		PersonUtil.logTestResult(testName.getMethodName(), assessee, finResult);

	}

}
