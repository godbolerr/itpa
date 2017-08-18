package com.work.itpa.web.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.work.itpa.rules.Address;
import com.work.itpa.rules.Donation;
import com.work.itpa.rules.FiConstants;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.rules.Income;
import com.work.itpa.rules.Investment;
import com.work.itpa.rules.Person;
import com.work.itpa.rules.PropertyDetails;
import com.work.itpa.service.TpaService;
import com.work.itpa.web.rest.util.PersonUtil;

/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/tpa")
public class TpaController {
	
	
	@Autowired
	TpaService service;

    @PostMapping("/benefits")
    @Timed
    public FinPersonResult calculateBenefits(@RequestBody FinPerson person) {    	
    	return service.calculateBenefits(person);
    }
    
    @GetMapping("/benefits")
    @Timed
    public FinPerson calculateBenefits() {    	
    	return new PersonUtil().getMarriedMaleWithOneDaughter();
    }
    
}
