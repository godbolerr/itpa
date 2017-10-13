/**
 * 
 */
package com.work.itpa.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * Rule used in the system
 * 
 * 
 *
 */
@Document(collection = "rules")
public class ItpaRule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	public String id;

	@Field("ruleId")
	public String ruleId;

	@Field("status")
	public String status;

	@Field("assessmentYear")
	public int assessmentYear;

	@Field("ruleText")
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
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
		ItpaRule other = (ItpaRule) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ruleId == null) {
			if (other.ruleId != null) {
				return false;
			}
		} else if (!ruleId.equals(other.ruleId)) {
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
		return "ItpaRule [id=" + id + ", ruleId=" + ruleId + ", status=" + status + ", assessmentYear=" + assessmentYear
				+ ", ruleText=" + ruleText + "]";
	}
	
	
	
	
	
	
	
	
}
