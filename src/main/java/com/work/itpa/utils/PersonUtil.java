/**
 * 
 */
package com.work.itpa.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.itpa.domain.Deduction;
import com.work.itpa.domain.Donation;
import com.work.itpa.domain.Expense;
import com.work.itpa.domain.FiConstants;
import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;
import com.work.itpa.domain.Income;
import com.work.itpa.domain.Investment;
import com.work.itpa.domain.Person;
import com.work.itpa.domain.PropertyDetails;
import com.work.itpa.domain.SummaryDeduction;

/**
 * Generate person data based on certain conditions
 * 
 * @author Developer
 *
 */
public class PersonUtil {

	public static String PERSON_NAME = "Ram Kumar";
	public static String PERSON_EMAIL = "rkumartyty@gmail.com";

	public static FinPerson getFinPerson() {

		FinPerson fPerson = new FinPerson(PERSON_NAME, FiConstants.RESIDENT_RESIDENT, new Date(),
				FiConstants.GENDER_MALE, FiConstants.RELATIONSHIP_SELF, 0, "");

		fPerson.setEmail(PERSON_EMAIL);
		fPerson.setContactNumber("+1 998 889 8888");
		fPerson.setAadharNumber("9998887878");
		fPerson.setPanNumber("OPDID9987A");
		fPerson.setAssesseeType(FiConstants.ASSESSEE_INDIVIDUAL);
		fPerson.setGrossTotalIncome(new BigDecimal("200000"));
		
		Person self = new Person();
		self.setFirstName("Ram");
		self.setLastName("Kumar");
		self.setAge(30);
		self.setRelationShipCode(FiConstants.RELATIONSHIP_SELF);
		self.setGender(FiConstants.GENDER_MALE);
		fPerson.addFamily(self);		
		
		
		fPerson.setEmploymentType("GOV");


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

	public static void addDonation(FinPerson person, BigDecimal amount, Donation.Type type, String note) {
		person.addDonation(new Donation(amount, type, note));
	}
	
	public static void addDonation(FinPerson person, BigDecimal amount, Donation.Type type, String schemeCode , String note) {
		person.addDonation(new Donation(amount, type,schemeCode, note));
	}	
	
	public static void addDonation(FinPerson person, double amount, Donation.Type type, String schemeCode , String note) {
		person.addDonation(new Donation(BigDecimal.valueOf(amount), type, note));
	}	

	public static void addExpense(FinPerson person, double amount, String type, String note) {
		person.addExpense(new Expense(BigDecimal.valueOf(amount), type, note));
	}


	public static void addExpense(FinPerson person, double amount, String relationShipCode, String type, String note) {
		Expense exp = new Expense(BigDecimal.valueOf(amount), type, note);
		exp.setRelationShipCode(relationShipCode);
		person.addExpense(exp);
	}
	
	public static void addIncome(FinPerson person, double amount, Income.Type type, Income.Source source, String note) {
		person.addIncome(new Income(BigDecimal.valueOf(amount), type, source, note));
	}

	public static void addPropertyDetails(FinPerson person, String name, String city, String status, double propertyValue, double loanAmount, double annualInterest, boolean firstProperty) {
		PropertyDetails details = new PropertyDetails(name,city,status);
		details.setLoanValue(BigDecimal.valueOf(loanAmount));
		details.setAnnualInterest(BigDecimal.valueOf(annualInterest));
		details.setFirstProperty(firstProperty);
		details.setPropertyValue(BigDecimal.valueOf(propertyValue));
		person.addPropertyDetails(details);
	}
	
	
	public static FinPerson getBachelorMale() {
		FinPerson person = getFinPerson();
		return person;
	}

	public static FinPerson getBachelorMaleWithWard() {
		FinPerson person = getFinPerson();
//		Person ward = new Person("Sham", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
//				FiConstants.RELATIONSHIP_WARD, 0, "");
//		person.addDependent(ward);
		return person;
	}
	
	public static Person getPerson(){
		
		Person self = new Person();
		self.setFirstName("Ram");
		self.setLastName("Kumar");
		self.setAge(30);
		self.setRelationShipCode(FiConstants.RELATIONSHIP_SELF);
	
		self.setGender(FiConstants.GENDER_MALE);

		return self;
		
//		return  new Person("XXX", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
//					FiConstants.RELATIONSHIP_HUFMEMBER, 0, "");
	}

	public static FinPerson getBachelorMaleWithHUFMember() {
		FinPerson person = getFinPerson();
//		Person hufMember = new Person("Laxman", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
//				FiConstants.RELATIONSHIP_HUFMEMBER, 0, "");
//		person.addDependent(hufMember);
		return person;
	}

	public static FinPerson getBachelorMaleAbove60() {
		FinPerson person = getFinPerson();
//		person.setAge(61);
		return person;
	}

	public static FinPerson getMarriedMale() {
		FinPerson finPerson = getFinPerson();
		finPerson.setMaritalStatus(FiConstants.MARITAL_MARRIED);
		
		Person wife = new Person();
		wife.setFirstName("Lata");
		wife.setLastName("Kulkarni");
		wife.setAge(30);
		//wife.setRelationShipCode(FiConstants.RELATIONSHIP_WIFE);
		wife.setGender(FiConstants.GENDER_FEMALE);
		finPerson.addFamily(wife);
		return finPerson;
	}

	public static FinPerson getMarriedMaleWithOneDaughter() {
		FinPerson person = getMarriedMale();
//		Person daughter = new Person("Aasha", FiConstants.RESIDENT_RESIDENT, new Date(), FiConstants.GENDER_FEMALE,
//				FiConstants.RELATIONSHIP_DAUGHTER, 0, "");
//		daughter.setAge(17);
//		person.addChildren(daughter);
		return person;
	}

	public static FinPerson addInvestment(FinPerson person, double amount, String type, String note) {
		person.addInvestment(new Investment(BigDecimal.valueOf(amount), type, note));
		return person;
	}

	public static boolean hasSectionWithAmount(List<Deduction> deductions, String section, double amount) {

		BigDecimal newAmount = BigDecimal.valueOf(amount);

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deduction.getAmount().compareTo(newAmount) == 0) {
				return true;
			}

		}
		return false;
	}
	
	
	public static boolean hasSectionWithAmount(List<Deduction> deductions, String section, BigDecimal amount) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction =  iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deduction.getAmount().equals(amount)) {
				return true;
			}

		}
		return false;
	}	

	public static boolean hasSection(List<Deduction> deductions, String section) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				return true;
			}

		}
		return false;
	}
	
	public static boolean hasSummarySection(List<SummaryDeduction> deductions, String section) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<SummaryDeduction> iterator = deductions.iterator(); iterator.hasNext();) {
			SummaryDeduction deduction = (SummaryDeduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				return true;
			}

		}
		return false;
	}	
	
	public static boolean hasSummarySectionWithAmount(List<SummaryDeduction> deductions, String sectionType, BigDecimal amount ) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator<SummaryDeduction> iterator = deductions.iterator(); iterator.hasNext();) {
			SummaryDeduction summaryDeduction = (SummaryDeduction) iterator.next();

			if (sectionType.equalsIgnoreCase(summaryDeduction.getSectionType())  &&  summaryDeduction.getEligibleAmount().equals(amount)            ) {
				return true;
			}

		}
		return false;
	}		
	

	public static boolean hasSectionNTimes(List<Deduction> deductions, String section, int noOfTimes) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		int count = 0;

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType())) {
				count++;
			}
		}

		if (count == noOfTimes) {
			return true;
		}
		return false;
	}

	public static boolean hasSectionWithDeductionTypeAndAmount(List<Deduction> deductions, String section,
			String deductionType, double amount) {

		BigDecimal newAmount = BigDecimal.valueOf(amount);

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deductionType.equalsIgnoreCase(deduction.getDeductionType())
					&& deduction.getAmount().compareTo(newAmount) == 0) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithDeductionType(List<Deduction> deductions, String section,
			String deductionType) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		for (Iterator iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = (Deduction) iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deductionType.equalsIgnoreCase(deduction.getDeductionType())

			) {
				return true;
			}

		}
		return false;
	}

	public static boolean hasSectionWithDeductionTypeNTimes(List<Deduction> deductions, String section,
			String deductionType, int noOfTimes) {

		if (deductions == null || deductions.size() == 0) {
			return false;
		}

		int count = 0;

		for (Iterator<Deduction> iterator = deductions.iterator(); iterator.hasNext();) {
			Deduction deduction = iterator.next();

			if (section.equalsIgnoreCase(deduction.getSectionType()) && deductionType.equalsIgnoreCase(deduction.getDeductionType())

			) {
				count++;
			}
		}

		if (count == noOfTimes) {
			return true;
		}
		return false;
	}
	
	public static void logTestResult(String testName, FinPerson finPerson, FinPersonResult result){
		
		
		
		String inputJson = testName + "_in.json";
		String outputJson = testName + "_out.json";
		
		ObjectMapper mapper = new ObjectMapper();
		
		finPerson.setAllPersons(null);
//		try {
//			mapper.writeValue(new File(inputJson), finPerson);
//			mapper.writeValue(new File(outputJson), result);
//			
//		} catch (JsonGenerationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	

}
