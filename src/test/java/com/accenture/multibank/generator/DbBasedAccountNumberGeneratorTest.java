package com.accenture.multibank.generator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import junit.framework.Assert;

public class DbBasedAccountNumberGeneratorTest {

	ApplicationContext appContext;
	JdbcTemplate template;
	DataSource ds;
	Connection connection;
	DbBasedAccountNumberGenerator numberGen;

	@Before
	public void createContext() throws SQLException {
		String sql;
		int result;

		appContext = new ClassPathXmlApplicationContext("mysqlTestContext.xml");
		ds = appContext.getBean("dataSource", DataSource.class);
		connection = ds.getConnection();
		numberGen = appContext.getBean("dbBasedNumberGenerator", DbBasedAccountNumberGenerator.class);

		// create table to hold account numbers
		sql = "CREATE TABLE IF NOT EXISTS account_numbers (numbers INTEGER AUTO_INCREMENT, Primary KEY(numbers))";
		result = connection.createStatement().executeUpdate(sql);

		// change incrementation start to another value
		sql = "ALTER TABLE account_numbers AUTO_INCREMENT = 100500";
		result = connection.createStatement().executeUpdate(sql);
	}

	@Test
	public void createANumber() {
		int accountNumber = numberGen.getNextAccountNumber();
		System.out.println(accountNumber);

	}

	@Test
	public void createMultipleNumbers() {
		Set<Integer> intSet = new HashSet<>();
		for (int i = 0; i < 1000; i++) {
			intSet.add(numberGen.getNextAccountNumber());

		}
		Assert.assertEquals(1000, intSet.size());
	}
}
