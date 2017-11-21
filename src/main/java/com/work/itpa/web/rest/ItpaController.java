package com.work.itpa.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.service.ItpaService;

/**
 * Responsible for serving web channel 
 * @author 115750
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
	public FinPersonResult getBenefits(@RequestBody com.work.itpa.rules.FinPerson fPerson) {
		return tpaService.calculateBenefits(fPerson);
	}
}
