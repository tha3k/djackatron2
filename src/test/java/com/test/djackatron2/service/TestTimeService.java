package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TestTimeService {
	
	@Test
	public void testIsServiceAvailable() {
	// given	
		Calendar openTime = new GregorianCalendar(2013,1,1,6,0);
		Calendar closeTime = new GregorianCalendar(2013,1,1,16,0);

		DefaultTimeService timeService = new DefaultTimeService();
		timeService.setOpenTime(openTime);
		timeService.setCloseTime(closeTime);
			
	// when
		assertEquals(true, timeService.isOpenService(new GregorianCalendar(2013,1,1,9,0)));
		assertEquals(false, timeService.isOpenService(new GregorianCalendar(2013,1,1,17,0)));
		
		
		
		
	}
}
