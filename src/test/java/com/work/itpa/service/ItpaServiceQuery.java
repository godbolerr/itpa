package com.work.itpa.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.work.itpa.ItpadocApp;
import com.work.itpa.rules.RuleData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ItpadocApp.class)
public class ItpaServiceQuery {

	
	@Autowired
	ItpaService dService;
	
	@Test
	public void test80D0() {
		List<RuleData> data = dService.getDecisionData(2017, "80D_0");
		
		assertTrue(data.size() > 0);
		
	}

}
