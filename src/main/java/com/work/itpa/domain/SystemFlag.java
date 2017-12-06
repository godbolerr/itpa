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

	/**
	 * @return the hraAvailed
	 */
	public Boolean getHraAvailed() {
		return hraAvailed;
	}

	/**
	 * @param hraAvailed the hraAvailed to set
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
	 * @param hasSalary the hasSalary to set
	 */
	public void setHasSalary(Boolean hasSalary) {
		this.hasSalary = hasSalary;
	}
	
	
}
