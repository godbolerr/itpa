package com.work.itpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
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
public class RuleTest {

	@Autowired
	ItpaService dService;

	@Test
	public void testResidentIndividualMaleBachelor() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		FinPersonResult result = dService.calculateBenefits(fPerson);
		// assertEquals(2, result.getMessages().size());
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeBelow10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 9000, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", 9000);

		assertEquals(true, result);
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeEqualTo10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 10000, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", 10000);

		assertTrue(result);
	}

	@Test
	public void test80TTAResidentIndividualSavingInterestIncomeGreaterThan10K() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 10001, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80TTA", 10001);

		assertFalse(result);
	}

	@Test
	public void test80RRBResidentIndividualOnePatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, FiConstants.INCOME_PATENT, "Income from Patent ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", 20000);

		assertTrue(result);
	}

	@Test
	public void test80RRBResidentIndividualTwoPatentIncome() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000, FiConstants.INCOME_PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, 22000, FiConstants.INCOME_PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", 20000);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", 22000);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80RRB", 42000);

		assertTrue(totalResult);

	}

	@Test
	public void test80RRBResidentIndividualThreePatentIncomeFractions() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 20000.14, FiConstants.INCOME_PATENT, "Income from Patent 1");
		PersonUtil.addIncome(fPerson, 22000.13, FiConstants.INCOME_PATENT, "Income from Patent 2 ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", 20000.14);

		assertTrue(result);

		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80RRB", 22000.13);

		assertTrue(result);

		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80RRB", 42000.27);

		assertTrue(totalResult);

	}

	@Test
	public void test80QQBResidentIndividualAuthorIncomeSingle() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 13000, FiConstants.INCOME_AUTHOR, "Income from Authoring book 1");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80QQB", 13000);

		assertTrue(result);

	}

	
	@Test
	public void test80QQBResidentIndividualAuthorIncomeDouble() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addIncome(fPerson, 24000.20, FiConstants.INCOME_AUTHOR, "Income from Authoring book 1");
		PersonUtil.addIncome(fPerson, 25000.50, FiConstants.INCOME_AUTHOR, "Income from Authoring book 2");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80QQB", 24000.20);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80QQB", 25000.50);

		assertTrue(result);		
		
		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80QQB", 49000.70);

		assertTrue(totalResult);		

	}
	
	@Test
	public void test80GGCResidentIndividualPoliticalDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_POLITICAL, "Donation to policical party xyz ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGC", 20000);

		assertTrue(result);
	}	
	
	
	@Test
	public void test80GGCResidentIndividualTwoPoliticalDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_POLITICAL, "Donation to policical party xyz ");
		PersonUtil.addDonation(fPerson, 40000, FiConstants.DONATION_POLITICAL, "Donation to policical party abc ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGC", 20000);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGC", 40000);

		assertTrue(result);
		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80GGC", 60000);

		assertTrue(totalResult);		
		
	}	
	
	

	@Test
	public void test80GGCResidentIndividualResearchDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_RESEARCH, "Donation to scientific research for biology");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGA", 20000);

		assertTrue(result);
	}	
	
	
	@Test
	public void test80GGCResidentIndividualTwoResearchDonations() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 2000000, FiConstants.DONATION_RESEARCH, "Donation to scientific research for biology ");
		PersonUtil.addDonation(fPerson, 4000000, FiConstants.DONATION_RESEARCH, "Donation to scientific research for chemistry ");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGA", 2000000);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80GGA", 4000000);

		assertTrue(result);
		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80GGA", 6000000);

		assertTrue(totalResult);		
		
	}	
	
	@Test
	public void test80GResidentIndividualDonation() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund", "Donation to Prime Minister’s National Relief Fund");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		// Verify section and amount deducted

		boolean result = PersonUtil.hasSectionWithAmount(finResult.getDeductions(), "80G", 20000);

		assertTrue(result);
	}	
	
	
	@Test
	public void test80GResidentIndividualDonationTwoTimesSameScheme() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund", "Donation to Prime Minister’s National Relief Fund");
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund", "Donation to Prime Minister’s National Relief Fund again");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80G", 40000);

		assertTrue(totalResult);
	}		
	
	@Test
	public void test80GResidentIndividualDonationTwoDifferentScheme() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, "Prime Minister’s National Relief Fund", "Donation to Prime Minister’s National Relief Fund");
		PersonUtil.addDonation(fPerson, 20000, "National Sports Fund", "Donation to National Sports Fund");
		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		
		boolean totalResult = PersonUtil.hasSectionWithAmount(finResult.getApplicableDeductions(), "80G", 40000);

		assertTrue(totalResult);
	}
	
	@Test
	public void test80ESelfResidentIndividual() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		
		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(),"80E");
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),"80E",1);

		assertTrue(result);
	}		

	@Test
	public void test80ESelfResidentIndividualMarried() {
		FinPerson fPerson = PersonUtil.getMarriedMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		
		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(),"80E");
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),"80E",2);

		assertTrue(result);
	}	
	
	@Test
	public void test80ESelfResidentIndividualWithWard() {
		FinPerson fPerson = PersonUtil.getBachelorMaleWithWard();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		
		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(),"80E");
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),"80E",2);

		assertTrue(result);
	}		
	
	@Test
	public void test80ESelfResidentHuf() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_HUF);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);
		
		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(),"80E");
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),"80E",1);

		assertTrue(result);
	}		

	@Test
	public void test80ESelfResidentHufWithHufMember() {
		FinPerson fPerson = PersonUtil.getBachelorMaleWithHUFMember();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_HUF);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);
		
		boolean result = PersonUtil.hasSection(finResult.getApplicableDeductions(),"80E");
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionNTimes(finResult.getDeductions(),"80E",2);

		assertTrue(result);
	}	
	
	
	@Test
	public void test80DSelfResidentIndividualBetween1And59() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setAge(45);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);
		
		boolean result = PersonUtil.hasSectionWithDeductionType(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE);
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionWithDeductionTypeNTimes(finResult.getDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE,1);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE,25000);

		assertTrue(result);	
		
		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_HEALTH_CHECKUP,5000);

		assertTrue(result);	
		
		
		
		
		
		
		
	}		
	
	
	@Test
	public void test80DSelfResidentIndividualAboveSixty() {
		FinPerson fPerson = PersonUtil.getBachelorMale();
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setAge(61);
		
		FinPersonResult finResult = dService.calculateBenefits(fPerson);
		
		boolean result = PersonUtil.hasSectionWithDeductionType(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE);
		
		assertTrue(result);
		
		result = PersonUtil.hasSectionWithDeductionTypeNTimes(finResult.getDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE,1);

		assertTrue(result);
		
		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_MECICAL_INSURANCE,30000);

		assertTrue(result);	
		
		result = PersonUtil.hasSectionWithDeductionTypeAndAmount(finResult.getApplicableDeductions(),"80D",FiConstants.DEDUCTION_HEALTH_CHECKUP,5000);

		assertTrue(result);	
		
		
		
		
		
		
		
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
