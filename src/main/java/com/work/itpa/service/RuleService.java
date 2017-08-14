package com.work.itpa.service;

import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;

/**
 * Service Interface for managing Person.
 */
public interface RuleService {

	public FinPersonResult calculateBenefits(FinPerson person);

}
