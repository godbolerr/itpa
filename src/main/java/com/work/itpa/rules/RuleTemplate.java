/**
 * 
 */
package com.work.itpa.rules;

import java.io.Serializable;

/**
 * 
 * Add trackers for date and audit
 * 
 * @author 115750
 *
 */
public class RuleTemplate implements Serializable {

	public String id;
	
	public String status;
	
	public String commaSeperatedFields;
	
	public int assessmentYear;
	
	public String ruleText;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the commaSeperatedFields
	 */
	public String getCommaSeperatedFields() {
		return commaSeperatedFields;
	}

	/**
	 * @param commaSeperatedFields the commaSeperatedFields to set
	 */
	public void setCommaSeperatedFields(String commaSeperatedFields) {
		this.commaSeperatedFields = commaSeperatedFields;
	}

	/**
	 * @return the assessmentYear
	 */
	public int getAssessmentYear() {
		return assessmentYear;
	}

	/**
	 * @param assessmentYear the assessmentYear to set
	 */
	public void setAssessmentYear(int assessmentYear) {
		this.assessmentYear = assessmentYear;
	}

	/**
	 * @return the ruleText
	 */
	public String getRuleText() {
		return ruleText;
	}

	/**
	 * @param ruleText the ruleText to set
	 */
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}
	
	
	
	
	
	
	
	}
