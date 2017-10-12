/**
 * 
 */
package com.work.itpa.rules;

import java.io.Serializable;

/**
 * 
 * Rule used in the system
 * 
 * @author 115750
 *
 */
public class ItpaRule implements Serializable {

	public String id;

	public String ruleId;

	public String status;

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
	 * @return the ruleId
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
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
