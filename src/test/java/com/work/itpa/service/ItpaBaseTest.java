package com.work.itpa.service;

import java.math.BigDecimal;
import java.util.Date;

import com.work.itpa.rules.Address;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.Income;
import com.work.itpa.rules.Investment;
import com.work.itpa.rules.Loan;
import com.work.itpa.rules.Person;
import com.work.itpa.rules.PropertyDetails;

public class ItpaBaseTest {

	public FinPerson getPerson() {

		FinPerson fPerson = new FinPerson("Raja Gosavi", new Date(), FiConstants.GENDER_MALE,
				FiConstants.RELATIONSHIP_SELF, 0, "");

		fPerson.setAge(40);
		fPerson.setDisabilityPercent(20);
		fPerson.setEmail("rgosavi@gmail.com");
		fPerson.setContactNumber("+1 998 889 8888");
		fPerson.setAadharNumber("9998887878");
		fPerson.setPanNumber("OPDID9987A");
		fPerson.setResidentialStatus("INDIAN");
		fPerson.setMaritalStatus("MARRIED");
		fPerson.setEmploymentType("GOV");
		fPerson.setSalaryBasicPerMonth(BigDecimal.valueOf(3939));
		fPerson.setSalaryPerAnum(BigDecimal.valueOf(49494949));
		fPerson.setPpfContributionPerMonth(BigDecimal.valueOf(4848));
		fPerson.setDearnessAllowancePerMonth(BigDecimal.valueOf(4949));
		fPerson.setPensionContributionPerMonth(BigDecimal.valueOf(9695));

		fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000), "LIC", "LIC 1"));
		fPerson.addInvestment(new Investment(BigDecimal.valueOf(20000), "LIC", "LIC 2"));
		fPerson.addInvestment(new Investment(BigDecimal.valueOf(35000), "LIC", "LIC 4"));
		
		fPerson.addDonation(new Donation(BigDecimal.valueOf(1000), "XX", "Money Given to charity 1"));
		fPerson.addDonation(new Donation(BigDecimal.valueOf(500), "YY", "PM Relief Fund"));
		fPerson.addIncome(new Income(BigDecimal.valueOf(1939), "Book", "Income from books"));
		fPerson.addIncome(new Income(BigDecimal.valueOf(111), "Patent", "Patent for new work"));
		fPerson.addLoan(new Loan(BigDecimal.valueOf(34949), BigDecimal.valueOf(24555), BigDecimal.valueOf(3455567),
				"HOUSING", "sbi", "33", new Date(), new Date()));
		fPerson.addPropertyDetails(new PropertyDetails("X House", "Pune", "OWN"));

		fPerson.setAddress(new Address("510 Bldg B", "Sea Colony", "Mumbai", "MH", "INDIA", "444001",
				"Near Rly Station", "PERSONAL"));

		fPerson.addChildren(new Person("Lata", new Date(), "Female", "WIFE", 0, ""));
		fPerson.addChildren(new Person("Sham", new Date(), "Male", "SON", 0, ""));
		fPerson.addChildren(new Person("Sita", new Date(), "Female", "DAUGHTER", 0, ""));

		fPerson.addDependent(new Person("Sitaram", new Date(), "Male", "FATHER", 20, "xxx"));
		fPerson.addDependent(new Person("Gauri", new Date(), "Female", "MOTHER", 90, "yyy"));

		return fPerson;

	}
	
	public void addDonation(FinPerson person, double amount, String orgName, String note){
		person.addDonation(new Donation(BigDecimal.valueOf(amount),orgName,note));
	}
	
	public void addIncome(FinPerson person, double amount, String reasonCode, String note){
		person.addIncome(new Income(BigDecimal.valueOf(amount),reasonCode,note));
	}
	

	
	

}
