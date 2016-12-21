package controller;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.multibank.Main;
import com.accenture.multibank.accounts.AccountType;
import com.accenture.multibank.bank.Bank;
import com.accenture.multibank.controller.BankController;
import com.accenture.multibank.entities.Transaction;

@SpringBootTest(classes = { Main.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class BankControllerTest {

	@MockBean
	Bank bank;
	@Autowired
	BankController bankController;


	@Test
	public void testcreateAccount() throws Exception {
		String actuell = bankController.createAccount(AccountType.SAVING);
		Assert.assertNotNull(actuell);
	}

	@Test
	public void testbook() throws Exception {
		when(bank.createAccount(AccountType.SAVING, 200)).thenReturn(1234);

		int Accnr1 = bank.createAccount(AccountType.SAVING, 200);
		String extreneAccNr = "Y" + Accnr1;
		Transaction transaction = new Transaction(extreneAccNr, null, 100);

		bankController.book(transaction);
	}


}
