/**
 * 
 */
package com.work.itpa.domain;

/**
 * Initiatlize with non null values
 * 
 * @author Developer
 *
 */
public class Disability {

	String percentSelf;

	String dependentDisabilityPercent;

	String dependentRelation;

	public Disability() {

		this.percentSelf = "";
		this.dependentDisabilityPercent = "";
		this.dependentRelation = "";
	}

	/**
	 * @param percentSelf
	 * @param dependentDisabilityPercent
	 * @param dependentRelation
	 */
	public Disability(String percentSelf, String dependentDisabilityPercent, String dependentRelation) {
		super();
		this.percentSelf = percentSelf;
		this.dependentDisabilityPercent = dependentDisabilityPercent;
		this.dependentRelation = dependentRelation;
	}

	/**
	 * @return the percentSelf
	 */
	public String getPercentSelf() {
		return percentSelf;
	}

	/**
	 * @param percentSelf
	 *            the percentSelf to set
	 */
	public void setPercentSelf(String percentSelf) {
		this.percentSelf = percentSelf;
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
	 * @return the dependentDisabilityPercent
	 */
	public String getDependentDisabilityPercent() {
		return dependentDisabilityPercent;
	}

	/**
	 * @param dependentDisabilityPercent the dependentDisabilityPercent to set
	 */
	public void setDependentDisabilityPercent(String dependentDisabilityPercent) {
		this.dependentDisabilityPercent = dependentDisabilityPercent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dependentDisabilityPercent == null) ? 0 : dependentDisabilityPercent.hashCode());
		result = prime * result + ((dependentRelation == null) ? 0 : dependentRelation.hashCode());
		result = prime * result + ((percentSelf == null) ? 0 : percentSelf.hashCode());
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
		Disability other = (Disability) obj;
		if (dependentDisabilityPercent == null) {
			if (other.dependentDisabilityPercent != null)
				return false;
		} else if (!dependentDisabilityPercent.equals(other.dependentDisabilityPercent))
			return false;
		if (dependentRelation == null) {
			if (other.dependentRelation != null)
				return false;
		} else if (!dependentRelation.equals(other.dependentRelation))
			return false;
		if (percentSelf == null) {
			if (other.percentSelf != null)
				return false;
		} else if (!percentSelf.equals(other.percentSelf))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Disability [percentSelf=" + percentSelf + ", dependentDisabilityPercent=" + dependentDisabilityPercent
				+ ", dependentRelation=" + dependentRelation + "]";
	}

}
