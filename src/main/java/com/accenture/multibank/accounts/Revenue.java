package com.accenture.multibank.accounts;

import java.util.Calendar;
import java.util.Date;

public class Revenue {

	private int revenue;
	private Date timestamp;

	public Revenue(int revenue) {
		this.revenue = revenue;
		this.timestamp = Calendar.getInstance().getTime();
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
