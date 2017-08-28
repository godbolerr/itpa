/**
 * 
 */
package com.work.itpa.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Contains information about the evaluator
 * 
 * @author developer
 *
 */
public class FinPerson extends Person {

	String email;

	String contactNumber;

	String panNumber;

	String aadharNumber;

	BigDecimal grossTotalIncome;

	Address address;

	List<Person> family;

	List<Person> dependents;

	/**
	 * Type of employment. TODO Enum
	 * 
	 */
	String employmentType;
	
	public String assesseeType;
	

	BigDecimal salaryPerAnum;

	BigDecimal salaryBasicPerMonth;

	BigDecimal pensionContributionPerMonth;

	BigDecimal ppfContributionPerMonth;

	BigDecimal dearnessAllowancePerMonth;

	boolean rentedAccomodation;
	
	boolean hraAvailed;

	List<PropertyDetails> propertyDetails;

	List<Loan> loans;
	
	List<Expense> expenses;

	List<Donation> donations;
	
	List<Investment> investments;
	
	List<Income> otherIncomes;
	
	List<Person> allPersons;
	
	boolean donationToPoliticalParty;
	boolean filedPatent;
	boolean authoredBook;
	boolean donationToResearch;
	boolean savingAccountPresent;
	

	public FinPerson() {
	}

	
	
	/**
	 * @param dateOfBirth
	 * @param gender
	 * @param relationShipCode
	 * @param disabilityPercent
	 * @param disease
	 */
	public FinPerson(String name,String residentStatus, Date dateOfBirth, String gender, String relationShipCode, int disabilityPercent, String disease) {
		super(name, residentStatus, dateOfBirth, gender, relationShipCode, disabilityPercent, disease);
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * @param panNumber
	 *            the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}

	/**
	 * @param aadharNumber
	 *            the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}


	/**
	 * @return the employmentType
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * @param employmentType
	 *            the employmentType to set
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * @return the salaryPerAnum
	 */
	public BigDecimal getSalaryPerAnum() {
		return salaryPerAnum;
	}

	/**
	 * @param salaryPerAnum
	 *            the salaryPerAnum to set
	 */
	public void setSalaryPerAnum(BigDecimal salaryPerAnum) {
		this.salaryPerAnum = salaryPerAnum;
	}

	/**
	 * @return the salaryBasicPerMonth
	 */
	public BigDecimal getSalaryBasicPerMonth() {
		return salaryBasicPerMonth;
	}

	/**
	 * @param salaryBasicPerMonth
	 *            the salaryBasicPerMonth to set
	 */
	public void setSalaryBasicPerMonth(BigDecimal salaryBasicPerMonth) {
		this.salaryBasicPerMonth = salaryBasicPerMonth;
	}

	/**
	 * @return the pensionContributionPerMonth
	 */
	public BigDecimal getPensionContributionPerMonth() {
		return pensionContributionPerMonth;
	}

	/**
	 * @param pensionContributionPerMonth
	 *            the pensionContributionPerMonth to set
	 */
	public void setPensionContributionPerMonth(BigDecimal pensionContributionPerMonth) {
		this.pensionContributionPerMonth = pensionContributionPerMonth;
	}

	/**
	 * @return the ppfContributionPerMonth
	 */
	public BigDecimal getPpfContributionPerMonth() {
		return ppfContributionPerMonth;
	}

	/**
	 * @param ppfContributionPerMonth
	 *            the ppfContributionPerMonth to set
	 */
	public void setPpfContributionPerMonth(BigDecimal ppfContributionPerMonth) {
		this.ppfContributionPerMonth = ppfContributionPerMonth;
	}

	/**
	 * @return the dearnessAllowancePerMonth
	 */
	public BigDecimal getDearnessAllowancePerMonth() {
		return dearnessAllowancePerMonth;
	}

	/**
	 * @param dearnessAllowancePerMonth
	 *            the dearnessAllowancePerMonth to set
	 */
	public void setDearnessAllowancePerMonth(BigDecimal dearnessAllowancePerMonth) {
		this.dearnessAllowancePerMonth = dearnessAllowancePerMonth;
	}

	/**
	 * @return the rentedAccomodation
	 */
	public boolean isRentedAccomodation() {
		return rentedAccomodation;
	}

	/**
	 * @param rentedAccomodation
	 *            the rentedAccomodation to set
	 */
	public void setRentedAccomodation(boolean rentedAccomodation) {
		this.rentedAccomodation = rentedAccomodation;
	}

	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans
	 *            the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	/**
	 * @return the donations
	 */
	public List<Donation> getDonations() {
		return donations;
	}

	/**
	 * @param donations
	 *            the donations to set
	 */
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	/**
	 * @return the otherIncomes
	 */
	public List<Income> getOtherIncomes() {
		return otherIncomes;
	}

	/**
	 * @return the propertyDetails
	 */
	public List<PropertyDetails> getPropertyDetails() {
		return propertyDetails;
	}

	/**
	 * @param propertyDetails
	 *            the propertyDetails to set
	 */
	public void setPropertyDetails(List<PropertyDetails> propertyDetails) {
		this.propertyDetails = propertyDetails;
	}

	/**
	 * @param otherIncomes
	 *            the otherIncomes to set
	 */
	public void setOtherIncomes(List<Income> otherIncomes) {
		this.otherIncomes = otherIncomes;
	}

	public void addPropertyDetails(PropertyDetails details) {
		if (this.propertyDetails == null) {
			this.propertyDetails = new ArrayList<PropertyDetails>();
		}
		this.propertyDetails.add(details);

	}

	public void addLoan(Loan loan) {
		if (this.loans == null) {
			this.loans = new ArrayList<Loan>();
		}
		loans.add(loan);

	}

	public void addDonation(Donation donation) {

		if (this.donations == null) {
			this.donations = new ArrayList<Donation>();
		}
		this.donations.add(donation);
	}

	public void addIncome(Income income) {

		if (this.otherIncomes == null) {
			this.otherIncomes = new ArrayList<Income>();
		}
		this.otherIncomes.add(income);
	}

	
	/**
	 * @return the dependents
	 */
	public List<Person> getDependents() {
		return dependents;
	}



	/**
	 * @param dependents the dependents to set
	 */
	public void setDependents(List<Person> dependents) {
		this.dependents = dependents;
	}



	public void addDependent(Person p ){
		
		if ( dependents == null ) {
			dependents = new ArrayList<Person>();
		}
		dependents.add(p);
	}
	
	public void addChildren(Person p ){
		
		if ( family == null ) {
			family = new ArrayList<Person>();
		}
		family.add(p);
	}

	public void addInvestment(Investment investment) {
		if (this.investments == null) {
			this.investments = new ArrayList<Investment>();
		}
		this.investments.add(investment);
	}

	public void addExpense(Expense expense) {
		if (this.expenses == null) {
			this.expenses = new ArrayList<Expense>();
		}
		this.expenses.add(expense);
	}
	
	
	/**
	 * @return the investments
	 */
	public List<Investment> getInvestments() {
		return investments;
	}



	/**
	 * @param investments the investments to set
	 */
	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}



	/**
	 * @return the family
	 */
	public List<Person> getFamily() {
		return family;
	}



	/**
	 * @param family the family to set
	 */
	public void setFamily(List<Person> family) {
		this.family = family;
	}



	/**
	 * @return the donationToPoliticalParty
	 */
	public boolean isDonationToPoliticalParty() {
		return donationToPoliticalParty;
	}



	/**
	 * @param donationToPoliticalParty the donationToPoliticalParty to set
	 */
	public void setDonationToPoliticalParty(boolean donationToPoliticalParty) {
		this.donationToPoliticalParty = donationToPoliticalParty;
	}



	/**
	 * @return the filedPatent
	 */
	public boolean isFiledPatent() {
		return filedPatent;
	}



	/**
	 * @param filedPatent the filedPatent to set
	 */
	public void setFiledPatent(boolean filedPatent) {
		this.filedPatent = filedPatent;
	}



	/**
	 * @return the authoredBook
	 */
	public boolean isAuthoredBook() {
		return authoredBook;
	}



	/**
	 * @param authoredBook the authoredBook to set
	 */
	public void setAuthoredBook(boolean authoredBook) {
		this.authoredBook = authoredBook;
	}



	/**
	 * @return the donationToResearch
	 */
	public boolean isDonationToResearch() {
		return donationToResearch;
	}



	/**
	 * @param donationToResearch the donationToResearch to set
	 */
	public void setDonationToResearch(boolean donationToResearch) {
		this.donationToResearch = donationToResearch;
	}



	/**
	 * @return the savingAccountPresent
	 */
	public boolean isSavingAccountPresent() {
		return savingAccountPresent;
	}



	/**
	 * @param savingAccountPresent the savingAccountPresent to set
	 */
	public void setSavingAccountPresent(boolean savingAccountPresent) {
		this.savingAccountPresent = savingAccountPresent;
	}



	/**
	 * @return the assesseeType
	 */
	public String getAssesseeType() {
		return assesseeType;
	}


	/**
	 * @param assesseeType the assesseeType to set
	 */
	public void setAssesseeType(String assesseeType) {
		this.assesseeType = assesseeType;
	}

	
	public List<Person> getAllPersons() {
		return allPersons;
	}



	public void setAllPersons(List<Person> allPersons) {
		this.allPersons = allPersons;
	}



	/**
	 * @return the grossTotalIncome
	 */
	public BigDecimal getGrossTotalIncome() {
		return grossTotalIncome;
	}



	/**
	 * @param grossTotalIncome the grossTotalIncome to set
	 */
	public void setGrossTotalIncome(BigDecimal grossTotalIncome) {
		this.grossTotalIncome = grossTotalIncome;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aadharNumber == null) ? 0 : aadharNumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((panNumber == null) ? 0 : panNumber.hashCode());

		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FinPerson other = (FinPerson) obj;
		if (aadharNumber == null) {
			if (other.aadharNumber != null) {
				return false;
			}
		} else if (!aadharNumber.equals(other.aadharNumber)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (panNumber == null) {
			if (other.panNumber != null) {
				return false;
			}
		} else if (!panNumber.equals(other.panNumber)) {
			return false;
		}

		return true;
	}




	/**
	 * @return the hraAvailed
	 */
	public boolean isHraAvailed() {
		return hraAvailed;
	}



	/**
	 * @param hraAvailed the hraAvailed to set
	 */
	public void setHraAvailed(boolean hraAvailed) {
		this.hraAvailed = hraAvailed;
	}



	/**
	 * @return the expenses
	 */
	public List<Expense> getExpenses() {
		return expenses;
	}



	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
	
	
}
