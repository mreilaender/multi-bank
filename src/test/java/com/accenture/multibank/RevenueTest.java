package com.accenture.multibank;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.accenture.multibank.accounts.Revenue;

public class RevenueTest {

	@Test
	public void getRevenueTest() {
		Revenue rev = new Revenue(100);

		Assert.assertEquals(100, rev.getRevenue());

	}

	@Test
	public void testDateSettingInRevenueObject() {
		Revenue rev = new Revenue(100);
		Date time = new Date();

		Assert.assertEquals(time, rev.getTimestamp());
	}

}
