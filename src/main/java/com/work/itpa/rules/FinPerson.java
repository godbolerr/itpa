/**
 * 
 */
package com.work.itpa.rules;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

	String residentialStatus;

	String maritalStatus;

	Address address;

	List<Person> family;

	List<Person> dependent;

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

	List<PropertyDetails> propertyDetails;

	List<Loan> loans;

	List<Donation> donations;
	
	List<Investment> investments;
	

	List<Income> otherIncomes;

	public FinPerson() {
	}

	
	
	/**
	 * @param dateOfBirth
	 * @param gender
	 * @param relationShipCode
	 * @param disabilityPercent
	 * @param disease
	 */
	public FinPerson(String name,LocalDate dateOfBirth, String gender, String relationShipCode, int disabilityPercent, String disease) {
		super(name,dateOfBirth, gender, relationShipCode, disabilityPercent, disease);
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
	 * @return the residentialStatus
	 */
	public String getResidentialStatus() {
		return residentialStatus;
	}

	/**
	 * @param residentialStatus
	 *            the residentialStatus to set
	 */
	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	 * @return the dependent
	 */
	public List<Person> getDependent() {
		return dependent;
	}

	/**
	 * @param dependent
	 *            the dependent to set
	 */
	public void setDependent(List<Person> dependent) {
		this.dependent = dependent;
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

	
	public void addDependent(Person p ){
		
		if ( dependent == null ) {
			dependent = new ArrayList<Person>();
		}
		dependent.add(p);
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
		result = prime * result + ((residentialStatus == null) ? 0 : residentialStatus.hashCode());
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
		if (residentialStatus == null) {
			if (other.residentialStatus != null) {
				return false;
			}
		} else if (!residentialStatus.equals(other.residentialStatus)) {
			return false;
		}
		return true;
	}
	
	
	
}
