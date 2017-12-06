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

}
