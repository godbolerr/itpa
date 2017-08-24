package com.work.itpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.itparules.ItparulesApplication;
import com.work.itpa.rules.FinPerson;
import com.work.itpa.rules.FinPersonResult;
import com.work.itpa.utils.PersonUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItparulesApplication.class)
public class RuleTest {
	
	@Autowired
	ItpaService dService;

	@Test
	public void testResidentIndividualMaleBachelor() {		
		FinPerson fPerson = PersonUtil.getBachelorMale();
		FinPersonResult result =   dService.calculateBenefits(fPerson);		
		//assertEquals(2, result.getMessages().size());
	}
}
