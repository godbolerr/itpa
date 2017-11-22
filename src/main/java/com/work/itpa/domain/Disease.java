/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

public class Disease {
	
	String name;
	
	String dependentRelation;
	
	String ageOfDependent;
	
	public BigDecimal amountSpent;
	
	public BigDecimal amountRecovered;

	
	
	/**
	 * @param name
	 * @param dependentRelation
	 * @param ageOfDependent
	 * @param amountSpent
	 * @param amountRecovered
	 */
	public Disease(String name, String dependentRelation, String ageOfDependent, BigDecimal amountSpent,
			BigDecimal amountRecovered) {
		super();
		this.name = name;
		this.dependentRelation = dependentRelation;
		this.ageOfDependent = ageOfDependent;
		this.amountSpent = amountSpent;
		this.amountRecovered = amountRecovered;
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
	 * @return the dependentRelation
	 */
	public String getDependentRelation() {
		return dependentRelation;
	}

	/**
	 * @param dependentRelation the dependentRelation to set
	 */
	public void setDependentRelation(String dependentRelation) {
		this.dependentRelation = dependentRelation;
	}

	/**
	 * @return the ageOfDependent
	 */
	public String getAgeOfDependent() {
		return ageOfDependent;
	}

	/**
	 * @param ageOfDependent the ageOfDependent to set
	 */
	public void setAgeOfDependent(String ageOfDependent) {
		this.ageOfDependent = ageOfDependent;
	}

	/**
	 * @return the amountSpent
	 */
	public BigDecimal getAmountSpent() {
		return amountSpent;
	}

	/**
	 * @param amountSpent the amountSpent to set
	 */
	public void setAmountSpent(BigDecimal amountSpent) {
		this.amountSpent = amountSpent;
	}

	/**
	 * @return the amountRecovered
	 */
	public BigDecimal getAmountRecovered() {
		return amountRecovered;
	}

	/**
	 * @param amountRecovered the amountRecovered to set
	 */
	public void setAmountRecovered(BigDecimal amountRecovered) {
		this.amountRecovered = amountRecovered;
	}
	
	
	

}
