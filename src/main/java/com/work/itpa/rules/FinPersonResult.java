package com.work.itpa.rules;

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
	
	List<Deduction> applicableDeductions = new ArrayList<Deduction>();
	
	List<Deduction> recoDeductions = new ArrayList<Deduction>();
	
	List<RiskScore> riskScores = new ArrayList<RiskScore>();
	
	double riskScore = 0.0;
	
	public Deduction getDeductionForType(String type){
		
		Deduction deduction = null;
		
		boolean found = false;
		
		
		for (Iterator iterator = applicableDeductions.iterator(); iterator.hasNext();) {
			Deduction d1 = (Deduction) iterator.next();
			if ( type.equals(d1.getSection()))
				found = true; 
				
		}
		
		if ( found == false ){
			deduction = new Deduction();
			applicableDeductions.add(deduction);
		}
		
		return deduction;
		
		
	}
	
	
	
	
	
	
	public void addMessage(String message) {
		messages.add(message);
	}

	public void addDeduction(Deduction deduction) {
		deductions.add(deduction);
	}

	public void addRecoDeduction(Deduction deduction) {
		recoDeductions.add(deduction);
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
	 * @param deductions the deductions to set
	 */
	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
	}



	/**
	 * @return the applicableDeductions
	 */
	public List<Deduction> getApplicableDeductions() {
		return applicableDeductions;
	}






	/**
	 * @param applicableDeductions the applicableDeductions to set
	 */
	public void setApplicableDeductions(List<Deduction> applicableDeductions) {
		this.applicableDeductions = applicableDeductions;
	}






	/**
	 * @return the recoDeductions
	 */
	public List<Deduction> getRecoDeductions() {
		return recoDeductions;
	}






	/**
	 * @param recoDeductions the recoDeductions to set
	 */
	public void setRecoDeductions(List<Deduction> recoDeductions) {
		this.recoDeductions = recoDeductions;
	}


	/**
	 * @return the riskScores
	 */
	public List<RiskScore> getRiskScores() {
		return riskScores;
	}






	/**
	 * @param riskScores the riskScores to set
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
	 * @param riskScore the riskScore to set
	 */
	public void setRiskScore(double riskScore) {
		this.riskScore = riskScore;
	}






	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinPersonResult [status=" + status + ", messages=" + messages + "]";
	}

}
