package com.test.djackatron2.service;

import java.util.Calendar;


public class DefaultTimeService {
	private Calendar openTime;
	private Calendar closeTime;

	
	
	public Calendar getOpenTime() {
		return openTime;
	}



	public void setOpenTime(Calendar openTime) {
		this.openTime = openTime;
	}



	public Calendar getCloseTime() {
		return closeTime;
	}



	public void setCloseTime(Calendar closeTime) {
		this.closeTime = closeTime;
	}



	public boolean isOpenService(Calendar testTime) {
		if (testTime.before(this.getOpenTime()) || testTime.after(this.getCloseTime()))
			return false;
		return true;
	}
	
	
}
