package com.work.itpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.DroolsConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DroolsConfig.class)
public class RuleServiceTest {
	
	@Autowired
	RuleService dService;

	@Test
	public void testCalculateBenefit() {

		System.out.println("Starting test ");
	}

}
