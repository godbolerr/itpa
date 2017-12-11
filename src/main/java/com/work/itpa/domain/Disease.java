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
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountRecovered == null) ? 0 : amountRecovered.hashCode());
		result = prime * result + ((amountSpent == null) ? 0 : amountSpent.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disease other = (Disease) obj;
		if (amountRecovered == null) {
			if (other.amountRecovered != null)
				return false;
		} else if (!amountRecovered.equals(other.amountRecovered))
			return false;
		if (amountSpent == null) {
			if (other.amountSpent != null)
				return false;
		} else if (!amountSpent.equals(other.amountSpent))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Disease [name=" + name + ", dependentRelation=" + dependentRelation + ", dependentAge=" + dependentAge
				+ ", amountSpent=" + amountSpent + ", amountRecovered=" + amountRecovered + "]";
	}

}
