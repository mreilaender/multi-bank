package com.accenture.multibank.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;

@SpringBootTest(classes = { Main.class })
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.test.properties")
public class BankControllerWithAspectTest {

	@Autowired
	BankController bankController;

	@Test(expected = Exception.class)
	public void testName() throws Exception {
		bankController.book(null);
	}

}
