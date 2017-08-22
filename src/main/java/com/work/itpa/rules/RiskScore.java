package com.work.itpa.rules;

public class RiskScore {
	
	/**
	 * Score assigned 
	 */
	int value;
	
	/**
	 * Note associated with score
	 */
	String note;

	
	
	public RiskScore(){}
	
	
	/**
	 * @param value
	 * @param note
	 */
	public RiskScore(int value, String note) {
		super();
		this.value = value;
		this.note = note;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RiskScore [value=" + value + ", " + (note != null ? "note=" + note : "") + "]";
	}
	

}
