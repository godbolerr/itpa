package com.work.itpa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Result of TPA evaluation
 * 
 * @author Developer
 *
 */
public class FinPersonResult {

	boolean status;

	List<String> messages = new ArrayList<String>();

	List<Deduction> deductions = new ArrayList<Deduction>();

	List<Deduction> summaryDeductions = new ArrayList<Deduction>();


	List<RiskScore> riskScores = new ArrayList<RiskScore>();

	double riskScore = 0.0;

	public Deduction getDeductionForType(String type) {

		Deduction deduction = null;

		boolean found = false;

		for (Iterator iterator = summaryDeductions.iterator(); iterator.hasNext();) {
			Deduction d1 = (Deduction) iterator.next();
			if (type.equals(d1.getSectionType()))
				found = true;

		}

		if (found == false) {
			deduction = new Deduction();
			deduction.setSectionType(type);
			summaryDeductions.add(deduction);
		}

		return deduction;

	}

	// What if there is no limit, like 100% is applicable,
	// Do we configure it somewhere ?
	
	public void addFinalDeduction(BigDecimal deductionAmount, String sectionType, String deductionType,BigDecimal maxLimit) {

		Deduction deduction = getDeductionForType(sectionType);
		
		// Check if existing limit is already reached.
		
		if ( deduction.getAmount().compareTo(maxLimit) == 0 ) {
			return;
		}
		
		// IF limit is not reached, then check if by adding current deduction, limit is maxed.
		
		BigDecimal currentAmount = deduction.getAmount().add(deductionAmount);
		
		if ( currentAmount.compareTo(maxLimit) == 1 || currentAmount.compareTo(maxLimit) == 0 ) {
			
			deduction.setAmount(maxLimit);
			
		} else {
			// Increment the value by the new amount
			
			deduction.setAmount(currentAmount);
		}
		
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public void addDeduction(Deduction deduction) {
		deductions.add(deduction);
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the deductions
	 */
	public List<Deduction> getDeductions() {
		return deductions;
	}

	/**
	 * @param deductions
	 *            the deductions to set
	 */
	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
	}

	/**
	 * @return the summaryDeductions
	 */
	public List<Deduction> getsummaryDeductions() {
		return summaryDeductions;
	}

	/**
	 * @param summaryDeductions
	 *            the summaryDeductions to set
	 */
	public void setsummaryDeductions(List<Deduction> summaryDeductions) {
		this.summaryDeductions = summaryDeductions;
	}


	/**
	 * @param recoDeductions
	 *            the recoDeductions to set
	 */

	/**
	 * @return the riskScores
	 */
	public List<RiskScore> getRiskScores() {
		return riskScores;
	}

	/**
	 * @param riskScores
	 *            the riskScores to set
	 */
	public void setRiskScores(List<RiskScore> riskScores) {
		this.riskScores = riskScores;
	}

	/**
	 * @return the riskScore
	 */
	public double getRiskScore() {
		return riskScore;
	}

	/**
	 * @param riskScore
	 *            the riskScore to set
	 */
	public void setRiskScore(double riskScore) {
		this.riskScore = riskScore;
	}
	
	

	/**
	 * @return the summaryDeductions
	 */
	public List<Deduction> getSummaryDeductions() {
		return summaryDeductions;
	}

	/**
	 * @param summaryDeductions the summaryDeductions to set
	 */
	public void setSummaryDeductions(List<Deduction> summaryDeductions) {
		this.summaryDeductions = summaryDeductions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinPersonResult [status=" + status + ", messages=" + messages + "]";
	}

}
