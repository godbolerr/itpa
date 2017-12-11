/**
 * 
 */
package com.work.itpa.domain;

import java.math.BigDecimal;

/**
 * Insurance Investments made by an assessee
 * 
 *
 */
public class Insurance {

	public String type;

	public String typeOfPolicy;

	public String policyFor;

	public BigDecimal premium;

	public BigDecimal sumAssured;

	public Integer healthCover;

	public String details;

	public Insurance(String type, BigDecimal premium, String details) {
		super();
		this.type = type;
		this.premium = premium;
		this.details = details;
	}

	/**
	 * @param type
	 * @param typeOfPolicy
	 * @param policyFor
	 * @param premium
	 * @param sumAssured
	 * @param healthCover
	 */
	public Insurance(String type, String typeOfPolicy, String policyFor, BigDecimal premium, BigDecimal sumAssured,
			Integer healthCover) {
		super();
		this.type = type;
		this.typeOfPolicy = typeOfPolicy;
		this.policyFor = policyFor;
		this.premium = premium;
		this.sumAssured = sumAssured;
		this.healthCover = healthCover;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the typeOfPolicy
	 */
	public String getTypeOfPolicy() {
		return typeOfPolicy;
	}

	/**
	 * @param typeOfPolicy
	 *            the typeOfPolicy to set
	 */
	public void setTypeOfPolicy(String typeOfPolicy) {
		this.typeOfPolicy = typeOfPolicy;
	}

	/**
	 * @return the policyFor
	 */
	public String getPolicyFor() {
		return policyFor;
	}

	/**
	 * @param policyFor
	 *            the policyFor to set
	 */
	public void setPolicyFor(String policyFor) {
		this.policyFor = policyFor;
	}

	/**
	 * @return the premium
	 */
	public BigDecimal getPremium() {
		return premium;
	}

	/**
	 * @param premium
	 *            the premium to set
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	/**
	 * @return the sumAssured
	 */
	public BigDecimal getSumAssured() {
		return sumAssured;
	}

	/**
	 * @param sumAssured
	 *            the sumAssured to set
	 */
	public void setSumAssured(BigDecimal sumAssured) {
		this.sumAssured = sumAssured;
	}

	/**
	 * @return the healthCover
	 */
	public Integer getHealthCover() {
		return healthCover;
	}

	/**
	 * @param healthCover
	 *            the healthCover to set
	 */
	public void setHealthCover(Integer healthCover) {
		this.healthCover = healthCover;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((policyFor == null) ? 0 : policyFor.hashCode());
		result = prime * result + ((premium == null) ? 0 : premium.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((typeOfPolicy == null) ? 0 : typeOfPolicy.hashCode());
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
		Insurance other = (Insurance) obj;
		if (policyFor == null) {
			if (other.policyFor != null)
				return false;
		} else if (!policyFor.equals(other.policyFor))
			return false;
		if (premium == null) {
			if (other.premium != null)
				return false;
		} else if (!premium.equals(other.premium))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeOfPolicy == null) {
			if (other.typeOfPolicy != null)
				return false;
		} else if (!typeOfPolicy.equals(other.typeOfPolicy))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Insurance [type=" + type + ", typeOfPolicy=" + typeOfPolicy + ", policyFor=" + policyFor + ", premium="
				+ premium + ", sumAssured=" + sumAssured + ", healthCover=" + healthCover + ", details=" + details
				+ "]";
	}
	
	
}
