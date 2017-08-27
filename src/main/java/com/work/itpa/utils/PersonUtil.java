/**
 * 
 */
package com.work.itpa.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.work.itpa.rules.Deduction;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.Income;
import com.work.itpa.rules.Investment;
import com.work.itpa.rules.Person;

/**
 * Generate person data based on certain conditions
 * 
 * @author Developer
 *
 */
public class PersonUtil {

	public static String PERSON_NAME = "Ram Kumar";
	public static String PERSON_EMAIL = "rkumartyty@gmail.com";

	public static FinPerson getPerson() {

		FinPerson fPerson = new FinPerson(PERSON_NAME, FiConstants.RESIDENT_RESIDENT, new Date(),
				FiConstants.GENDER_MALE, FiConstants.RELATIONSHIP_SELF, 0, "");

		fPerson.setEmail(PERSON_EMAIL);
		fPerson.setContactNumber("+1 998 889 8888");
		fPerson.setAadharNumber("9998887878");
		fPerson.setPanNumber("OPDID9987A");
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setAge(40);
		fPerson.setEmploymentType("GOV");
		fPerson.setSalaryBasicPerMonth(BigDecimal.valueOf(3939));
		fPerson.setSalaryPerAnum(BigDecimal.valueOf(49494949));
		fPerson.setPpfContributionPerMonth(BigDecimal.valueOf(4848));
		fPerson.setDearnessAllowancePerMonth(BigDecimal.valueOf(4949));
		fPerson.setPensionContributionPerMonth(BigDecimal.valueOf(9695));

		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000),
		// "LIC", "LIC 1"));
		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(20000),
		// "LIC", "LIC 2"));
		// fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000),
		// "PPF", "PPF 1"));
		//
		// fPerson.addDonation(new Donation(BigDecimal.valueOf(1000), "XX",
		// "Money Given to charity 1"));
		// fPerson.addDonation(new Donation(BigDecimal.valueOf(500), "YY", "PM
		// Relief Fund"));
		// fPerson.addIncome(new Income(BigDecimal.valueOf(1939), "Book",
		// "Income from books"));
		// fPerson.addIncome(new Income(BigDecimal.valueOf(111), "Patent",
		// "Patent for new work"));
		// fPerson.addLoan(new Loan(BigDecimal.valueOf(34949),
		// BigDecimal.valueOf(24555), BigDecimal.valueOf(3455567),
		// "HOUSING", "sbi", "33", new Date(), new Date()));
		// fPerson.addPropertyDetails(new PropertyDetails("X House", "Pune",
		// "OWN"));
		//
		// fPerson.setAddress(new Address("510 Bldg B", "Sea Colony", "Mumbai",
		// "MH", "INDIA", "444001",
		// "Near Rly Station", "PERSONAL"));
		//
		// fPerson.addChildren(new Person("Lata", new Date(), "Female", "WIFE",
		// 0, ""));
		// fPerson.addChildren(new Person("Sham", new Date(), "Male", "SON", 0,
		// ""));
		// fPerson.addChildren(new Person("Sita", new Date(), "Female",
		// "DAUGHTER", 0, ""));
		//
		// fPerson.addDependent(new Person("Sitaram", new Date(), "Male",
		// "FATHER", 20, "xxx"));
		// fPerson.addDependent(new Person("Gauri", new Date(), "Female",
		// "MOTHER", 90, "yyy"));

		return fPerson;

	}

	public static void addDonation(FinPerson person, double amount, String orgName, String note) {
		person.addDonation(new Donation(BigDecimal.valueOf(amount), orgName, note));
	}

	public static void addIncome(FinPerson person, double amount, String reasonCode, String note) {
		person.addIncome(new Income(BigDecimal.valueOf(amount), reasonCode, note));
	}

	public static FinPerson getBachelorMale() {
		FinPerson person = getPerson();
		return person;
	}

	public static FinPerson getBachelorMaleAbove60() {
		FinPerson person = getPerson();
		person.setAge(61);
		return person;
	}

	public static FinPerson getMarriedMale() {
		FinPerson person = getPerson();
		person.setMaritalStatus(FiConstants.MARITAL_MARRIED);
		Person wife = new Person("Lata", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
				FiConstants.RELATIONSHIP_WIFE, 0, "");
		wife.setAge(37);
		person.addChildren(wife);
		return person;
	}

	public static FinPerson getMarriedMaleWithOneDaughter() {
		FinPerson person = getMarriedMale();
		Person daughter = new Person("Aasha", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
				FiConstants.RELATIONSHIP_DAUGHTER, 0, "");
		daughter.setAge(17);
		person.addChildren(daughter);
		return person;
	}

	public static FinPerson addInvestment(FinPerson person, double amount, String type, String note) {
		person.addInvestment(new Investment(BigDecimal.valueOf(amount), type, note));
		return person;
	}

	public static boolean hasSectionWithAmount(List<Deduction> deductions, String section, BigDecimal amount) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSection()) && deduction.getAmount().compareTo(amount) == 0) {
				return true;
			}

		}
		return false;
	}

}