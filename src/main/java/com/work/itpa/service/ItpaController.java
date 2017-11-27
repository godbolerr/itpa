package com.work.itpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.itpa.domain.FinPerson;
import com.work.itpa.domain.FinPersonResult;


/**
 * Responsible for serving web channel 
 * 
 *
 */
@RestController
@RequestMapping("/itpa")
public class ItpaController {

	@Autowired
	private ItpaService tpaService;

	public ItpaController() {
	}

	@RequestMapping(value = "/benefits", method = { RequestMethod.POST })
	public FinPersonResult getBenefits(@RequestBody FinPerson fPerson) {
		return tpaService.calculateBenefits(fPerson);
	}
}
