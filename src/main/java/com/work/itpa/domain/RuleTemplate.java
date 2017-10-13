/**
 * 
 */
package com.work.itpa.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Responsible for template management
 * 
 * @author ITPA
 *
 */

@Document(collection = "ruleTemplate")
public class RuleTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	public String id;

	@Field("ruleTemplateId")
	public String ruleTemplateId;

	@Field("status")
	public String status;

	@Field("commaSeperatedFields")
	public String commaSeperatedFields;

	@Field("assessmentYear")
	public int assessmentYear;

	@Field("ruleText")
	public String ruleText;
	
	public RuleTemplate(){}

	/**
	 * @param id
	 * @param ruleTemplateId
	 * @param status
	 * @param commaSeperatedFields
	 * @param assessmentYear
	 * @param ruleText
	 */
	public RuleTemplate(String id, String ruleTemplateId, String status, String commaSeperatedFields,
			int assessmentYear, String ruleText) {
		super();
		this.id = id;
		this.ruleTemplateId = ruleTemplateId;
		this.status = status;
		this.commaSeperatedFields = commaSeperatedFields;
		this.assessmentYear = assessmentYear;
		this.ruleText = ruleText;
	}	

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
	 * @return the ruleTemplateId
	 */
	public String getRuleTemplateId() {
		return ruleTemplateId;
	}

	/**
	 * @param ruleTemplateId the ruleTemplateId to set
	 */
	public void setRuleTemplateId(String ruleTemplateId) {
		this.ruleTemplateId = ruleTemplateId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assessmentYear;
		result = prime * result + ((commaSeperatedFields == null) ? 0 : commaSeperatedFields.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ruleTemplateId == null) ? 0 : ruleTemplateId.hashCode());
		result = prime * result + ((ruleText == null) ? 0 : ruleText.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RuleTemplate other = (RuleTemplate) obj;
		if (assessmentYear != other.assessmentYear) {
			return false;
		}
		if (commaSeperatedFields == null) {
			if (other.commaSeperatedFields != null) {
				return false;
			}
		} else if (!commaSeperatedFields.equals(other.commaSeperatedFields)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ruleTemplateId == null) {
			if (other.ruleTemplateId != null) {
				return false;
			}
		} else if (!ruleTemplateId.equals(other.ruleTemplateId)) {
			return false;
		}
		if (ruleText == null) {
			if (other.ruleText != null) {
				return false;
			}
		} else if (!ruleText.equals(other.ruleText)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RuleTemplate [id=" + id + ", ruleTemplateId=" + ruleTemplateId + ", status=" + status
				+ ", commaSeperatedFields=" + commaSeperatedFields + ", assessmentYear=" + assessmentYear
				+ ", ruleText=" + ruleText + "]";
	}

	
	
	
}
