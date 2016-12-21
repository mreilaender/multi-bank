package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.controller.BankController;

@SpringBootTest(classes = { Main.class })
@RunWith(SpringJUnit4ClassRunner.class)

public class BankControllerWithAspectTest {

	@Autowired
	BankController bankController;

	@Test(expected = Exception.class)
	public void testName() throws Exception {
		bankController.book(null);
	}

}
