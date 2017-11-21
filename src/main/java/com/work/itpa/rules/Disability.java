/**
 * 
 */
package com.work.itpa.rules;


public class Disability {

	
	String percentSelf;
	
	String percentDependent;
	
	String dependentRelation;
	
	
	

	/**
	 * @param percentSelf
	 * @param percentDependent
	 * @param dependentRelation
	 */
	public Disability(String percentSelf, String percentDependent, String dependentRelation) {
		super();
		this.percentSelf = percentSelf;
		this.percentDependent = percentDependent;
		this.dependentRelation = dependentRelation;
	}

	/**
	 * @return the percentSelf
	 */
	public String getPercentSelf() {
		return percentSelf;
	}

	/**
	 * @param percentSelf the percentSelf to set
	 */
	public void setPercentSelf(String percentSelf) {
		this.percentSelf = percentSelf;
	}

	/**
	 * @return the percentDependent
	 */
	public String getPercentDependent() {
		return percentDependent;
	}

	/**
	 * @param percentDependent the percentDependent to set
	 */
	public void setPercentDependent(String percentDependent) {
		this.percentDependent = percentDependent;
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
	
	
}
