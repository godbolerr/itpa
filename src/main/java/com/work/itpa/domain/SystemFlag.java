/**
 * 
 */
package com.work.itpa.domain;

/**
 * Property owned by Assessee
 * 
 * @author Developer
 *
 */
public class SystemFlag {

	Boolean hraAvailed;

	Boolean hasSalary;

	Boolean hasDisabilitySelf;

	Boolean hasDisabledDependent;

	public SystemFlag() {

		hraAvailed = Boolean.FALSE;

		hasSalary = Boolean.FALSE;

		hasDisabilitySelf = Boolean.FALSE;

		hasDisabledDependent = Boolean.FALSE;

	}

	/**
	 * @return the hraAvailed
	 */
	public Boolean getHraAvailed() {
		return hraAvailed;
	}

	/**
	 * @param hraAvailed
	 *            the hraAvailed to set
	 */
	public void setHraAvailed(Boolean hraAvailed) {
		this.hraAvailed = hraAvailed;
	}

	/**
	 * @return the hasSalary
	 */
	public Boolean getHasSalary() {
		return hasSalary;
	}

	/**
	 * @param hasSalary
	 *            the hasSalary to set
	 */
	public void setHasSalary(Boolean hasSalary) {
		this.hasSalary = hasSalary;
	}

	/**
	 * @return the hasDisabilitySelf
	 */
	public Boolean getHasDisabilitySelf() {
		return hasDisabilitySelf;
	}

	/**
	 * @param hasDisabilitySelf
	 *            the hasDisabilitySelf to set
	 */
	public void setHasDisabilitySelf(Boolean hasDisabilitySelf) {
		this.hasDisabilitySelf = hasDisabilitySelf;
	}

	/**
	 * @return the hasDisabledDependent
	 */
	public Boolean getHasDisabledDependent() {
		return hasDisabledDependent;
	}

	/**
	 * @param hasDisabledDependent the hasDisabledDependent to set
	 */
	public void setHasDisabledDependent(Boolean hasDisabledDependent) {
		this.hasDisabledDependent = hasDisabledDependent;
	}

}
