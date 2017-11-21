/**
 * 
 */
package com.work.itpa.rules;


public class Disability {

	
	int percentSelf;
	
	int percentDependent;
	
	String dependentRelation;
	
	
	

	/**
	 * @param percentSelf
	 * @param percentDependent
	 * @param dependentRelation
	 */
	public Disability(int percentSelf, int percentDependent, String dependentRelation) {
		super();
		this.percentSelf = percentSelf;
		this.percentDependent = percentDependent;
		this.dependentRelation = dependentRelation;
	}

	/**
	 * @return the percentSelf
	 */
	public int getPercentSelf() {
		return percentSelf;
	}

	/**
	 * @param percentSelf the percentSelf to set
	 */
	public void setPercentSelf(int percentSelf) {
		this.percentSelf = percentSelf;
	}

	/**
	 * @return the percentDependent
	 */
	public int getPercentDependent() {
		return percentDependent;
	}

	/**
	 * @param percentDependent the percentDependent to set
	 */
	public void setPercentDependent(int percentDependent) {
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
