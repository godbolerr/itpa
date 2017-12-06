/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

public class Disease {

	String name;

	String dependentRelation;

	String dependentAge;

	public BigDecimal amountSpent;

	public BigDecimal amountRecovered;

	public Disease() {

		this.name = "";
		this.dependentRelation = "";
		this.dependentAge = "";

	}

	public Disease(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 * @param dependentRelation
	 * @param dependentAge
	 * @param amountSpent
	 * @param amountRecovered
	 */
	public Disease(String name, String dependentRelation, String dependentAge, BigDecimal amountSpent,
			BigDecimal amountRecovered) {
		super();
		this.name = name;
		this.dependentRelation = dependentRelation;
		this.dependentAge = dependentAge;
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
	 * @param name
	 *            the name to set
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
	 * @param dependentRelation
	 *            the dependentRelation to set
	 */
	public void setDependentRelation(String dependentRelation) {
		this.dependentRelation = dependentRelation;
	}

	/**
	 * @return the amountSpent
	 */
	public BigDecimal getAmountSpent() {
		return amountSpent;
	}

	/**
	 * @param amountSpent
	 *            the amountSpent to set
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
	 * @param amountRecovered
	 *            the amountRecovered to set
	 */
	public void setAmountRecovered(BigDecimal amountRecovered) {
		this.amountRecovered = amountRecovered;
	}

	/**
	 * @return the dependentAge
	 */
	public String getDependentAge() {
		return dependentAge;
	}

	/**
	 * @param dependentAge the dependentAge to set
	 */
	public void setDependentAge(String dependentAge) {
		this.dependentAge = dependentAge;
	}

}
