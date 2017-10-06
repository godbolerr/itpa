package com.work.itpa.service;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.Address;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Investment;
import com.work.itpa.rules.Loan;
import com.work.itpa.rules.Person;
import com.work.itpa.web.rest.util.PersonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class InputFinPersonTest {

	@Rule
	public TestName testName = new TestName();

	@Autowired
	ItpaService dService;

	String sectionName = "80U";

	@Test
	public void generateModelPerson() {
		FinPerson fPerson = PersonUtil.getMarriedMaleWithOneDaughter();
		fPerson.selfPerson().setAge(41);
		fPerson.selfPerson().setDisabilityPercent(50);
		fPerson.setResidentStatus(FiConstants.RESIDENT_RESIDENT);
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(BigDecimal.valueOf(49494949));
		
				
		fPerson.setAddress(new Address("D 334, Range Hill","Near Blue Hotel","Pune","Maharashtra","India","444033","Express way entry","RESIDENT"));

		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_POLITICAL, "Donation to policical party xyz ");
		PersonUtil.addIncome(fPerson, 9000, FiConstants.INCOME_SAVINGINTEREST, "Interest from savings bank ");
		PersonUtil.addIncome(fPerson, 20000, FiConstants.INCOME_PATENT, "Income from Patent ");
		PersonUtil.addIncome(fPerson, 13000, FiConstants.INCOME_AUTHOR, "Income from Authoring book 1");
		PersonUtil.addExpense(fPerson, 50000, FiConstants.EXPENSE_RENT, "Rent paid per anum ");
		PersonUtil.addDonation(fPerson, 20000, FiConstants.DONATION_RESEARCH,
				"Donation to scientific research for biology");
		Donation donation = new Donation(BigDecimal.valueOf(20000), "", "Test G1 Type donations");
		donation.setSchemeCode("G1_1");
		fPerson.addDonation(donation);

		fPerson.addLoan(new Loan(BigDecimal.valueOf(34949), BigDecimal.valueOf(24555), BigDecimal.valueOf(3455567),
				"HOUSING", "sbi", "33", new Date(), new Date()));

		fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000), "LIC", "LIC 1"));
		fPerson.addInvestment(new Investment(BigDecimal.valueOf(20000), "LIC", "LIC 2"));
		fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000), "PPF", "PPF 1"));

		PersonUtil.addExpense(fPerson, 50000, FiConstants.RELATIONSHIP_SELF,
				FiConstants.EXPENSE_HIGHER_EDU_LOAN_INTEREST, "Interest Paid for loan on higher education of self");

		PersonUtil.addPropertyDetails(fPerson, "Pune House", "Pune", FiConstants.OWNERSHIP_OWN, 4000000, 3000000, 40000,
				true);

		Person wife = PersonUtil.getPerson();

		wife.setDisabilityPercent(45);
		wife.setGender(FiConstants.GENDER_FEMALE);
		wife.setRelationShipCode(FiConstants.RELATIONSHIP_WIFE);
		wife.setName("Aasha");
		wife.setAge(34);

		fPerson.addPerson(wife);

		Person father = PersonUtil.getPerson();

		father.setGender(FiConstants.GENDER_MALE);
		father.setRelationShipCode(FiConstants.RELATIONSHIP_FATHER);
		father.setName("xxxx");
		father.setDisease("ABCD");
		father.setAge(62);

		fPerson.addPerson(father);

		Person mother = PersonUtil.getPerson();

		mother.setGender(FiConstants.GENDER_FEMALE);
		mother.setRelationShipCode(FiConstants.RELATIONSHIP_MOTHER);
		mother.setName("xxxx");
		mother.setDisease("ABCD");
		mother.setAge(82);

		fPerson.addPerson(mother);

		FinPersonResult finResult = dService.calculateBenefits(fPerson);

		PersonUtil.logTestResult(testName.getMethodName(), fPerson, finResult);

	}

}
