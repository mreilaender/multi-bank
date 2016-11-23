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

	/**
	 * @return the revenue
	 */
	public int getRevenue() {
		return revenue;
	}

	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

}
