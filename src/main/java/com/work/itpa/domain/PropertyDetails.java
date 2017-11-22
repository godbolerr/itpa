/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

/**
 * Property owned by Assessee
 * 
 * @author Developer
 *
 */
public class PropertyDetails {
	
	
	String name;
	
	
	String city;
	
	/**
	 * Owned, Rented
	 */
	String status;
	
	
	public boolean firstProperty;	
	
	public BigDecimal loanValue;
	
	public BigDecimal propertyValue;
	
	public BigDecimal annualInterest;
	
	
	public PropertyDetails(){}
	/**
	 * @param name
	 * @param city
	 * @param status
	 */
	public PropertyDetails(String name, String city, String status) {
		super();
		this.name = name;
		this.city = city;
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the firstProperty
	 */
	public boolean isFirstProperty() {
		return firstProperty;
	}
	/**
	 * @param firstProperty the firstProperty to set
	 */
	public void setFirstProperty(boolean firstProperty) {
		this.firstProperty = firstProperty;
	}
	/**
	 * @return the loanValue
	 */
	public BigDecimal getLoanValue() {
		return loanValue;
	}
	/**
	 * @param loanValue the loanValue to set
	 */
	public void setLoanValue(BigDecimal loanValue) {
		this.loanValue = loanValue;
	}
	/**
	 * @return the annualInterest
	 */
	public BigDecimal getAnnualInterest() {
		return annualInterest;
	}
	/**
	 * @param annualInterest the annualInterest to set
	 */
	public void setAnnualInterest(BigDecimal annualInterest) {
		this.annualInterest = annualInterest;
	}
	/**
	 * @return the propertyValue
	 */
	public BigDecimal getPropertyValue() {
		return propertyValue;
	}
	/**
	 * @param propertyValue the propertyValue to set
	 */
	public void setPropertyValue(BigDecimal propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	

}
