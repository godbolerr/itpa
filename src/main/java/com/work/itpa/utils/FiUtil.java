package com.work.itpa.utils;

import java.math.BigDecimal;

import com.work.itpa.domain.Deduction;
import com.work.itpa.domain.SummaryDeduction;

/**
 * Utility class to be used in actual rules
 * 
 * @author Developer
 *
 */
public class FiUtil {
	
	
	public static boolean isSummmaryGreaterThanEligible(SummaryDeduction sDeduction){
		
		if (sDeduction.getEligibleAmount().compareTo(sDeduction.getMaxAmount()) > 0) {
			return true;
		}
		return false;
	}

	public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {

		if (first.compareTo(second) > 0) {
			return true;
		}

		return false;

	}

	/**
	 * For now it is assumed to when NO MAX LIMIT is defined, it is marked as 0.
	 * 
	 * TODO : Check the requirement
	 * 
	 * @param deduction
	 * @param sDeduction
	 * @return
	 */

	public static boolean isLessThanEqualToMax(Deduction deduction, SummaryDeduction sDeduction) {

		boolean status = false;

		BigDecimal result = sDeduction.getEligibleAmount().add(deduction.getEligibleDeduction());

		if (sDeduction.getMaxAmount().compareTo(new BigDecimal("0")) == 0) {
			status = true;

		} else {

			if (result.compareTo(sDeduction.getMaxAmount()) >= 0) {
				status = false;
			} else {
				status = true;
			}
		}

		return status;

	}

	/**
	 * Returns percentage value for a given BigDecimal object.
	 * 
	 * @param input
	 * @param percent
	 * @return
	 */
	public static BigDecimal percentOf(BigDecimal input, int deductionPercent) {

		BigDecimal result =  input.multiply(new BigDecimal(deductionPercent)).divide(new BigDecimal(100));
		return result;

	}

	public static boolean isAvailable(String x, String type) {

		boolean result = false;

		if (type.equalsIgnoreCase("80c")) {

			if (x.equalsIgnoreCase("PPF")) {
				result = true;
			}
		}

		return result;
	}

	public static BigDecimal lessOf(BigDecimal value1, BigDecimal value2) {

		if (value1.compareTo(value2) == -1) {
			return value1;
		}
		return value2;
	}

	public static BigDecimal maxOf(BigDecimal value1, BigDecimal value2) {

		if (value1.compareTo(value2) == 1) {
			return value1;
		}
		return value2;
	}

	public static BigDecimal maxOf(BigDecimal value1, double anotherValue) {

		BigDecimal value2 = BigDecimal.valueOf(anotherValue);

		if (value1.compareTo(value2) == 1) {
			return value1;
		}
		return value2;
	}

	public static BigDecimal maxOf(double firstValue, double anotherValue) {

		BigDecimal value1 = BigDecimal.valueOf(firstValue);
		BigDecimal value2 = BigDecimal.valueOf(anotherValue);

		if (value1.compareTo(value2) == 1) {
			return value1;
		}
		return value2;
	}

}
