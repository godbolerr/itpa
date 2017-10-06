/**
 * 
 */
package com.work.itpa.rules;

import java.io.Serializable;

/**
 * 
 * _id,assessmentYear,ruleTemplate,status,name,,,,,,,,,,ruleTemplate,status,name,section,residentStatus,assesseeType,relationshipCode,deductionType,minAge,maxAge,maxDeduction
 * 
 * @author 115750
 *
 */
public class RuleData implements Serializable {

	String id;
	int assessmentYear;
	String ruleTemplate;
	String status;
	String name;
	String section;
	String residetStatus;
	String assesseeType;
	String relationshipCode;
	String deductionType;
	int minAge;
	int maxAge;
	int maxDeduction;
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
	 * @return the ruleTemplate
	 */
	public String getRuleTemplate() {
		return ruleTemplate;
	}
	/**
	 * @param ruleTemplate the ruleTemplate to set
	 */
	public void setRuleTemplate(String ruleTemplate) {
		this.ruleTemplate = ruleTemplate;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the residetStatus
	 */
	public String getResidetStatus() {
		return residetStatus;
	}
	/**
	 * @param residetStatus the residetStatus to set
	 */
	public void setResidetStatus(String residetStatus) {
		this.residetStatus = residetStatus;
	}
	/**
	 * @return the assesseeType
	 */
	public String getAssesseeType() {
		return assesseeType;
	}
	/**
	 * @param assesseeType the assesseeType to set
	 */
	public void setAssesseeType(String assesseeType) {
		this.assesseeType = assesseeType;
	}
	/**
	 * @return the relationshipCode
	 */
	public String getRelationshipCode() {
		return relationshipCode;
	}
	/**
	 * @param relationshipCode the relationshipCode to set
	 */
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}
	/**
	 * @param deductionType the deductionType to set
	 */
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
	/**
	 * @return the minAge
	 */
	public int getMinAge() {
		return minAge;
	}
	/**
	 * @param minAge the minAge to set
	 */
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	/**
	 * @return the maxAge
	 */
	public int getMaxAge() {
		return maxAge;
	}
	/**
	 * @param maxAge the maxAge to set
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	/**
	 * @return the maxDeduction
	 */
	public int getMaxDeduction() {
		return maxDeduction;
	}
	/**
	 * @param maxDeduction the maxDeduction to set
	 */
	public void setMaxDeduction(int maxDeduction) {
		this.maxDeduction = maxDeduction;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RuleData [id=" + id + ", assessmentYear=" + assessmentYear + ", ruleTemplate=" + ruleTemplate
				+ ", status=" + status + ", name=" + name + ", section=" + section + ", residetStatus=" + residetStatus
				+ ", assesseeType=" + assesseeType + ", relationshipCode=" + relationshipCode + ", deductionType="
				+ deductionType + ", minAge=" + minAge + ", maxAge=" + maxAge + ", maxDeduction=" + maxDeduction + "]";
	}
	
	
	

}
