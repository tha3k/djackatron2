package com.test.djackatron2.service;

import org.joda.time.LocalTime;

public class TimeService {
	private LocalTime openService;
	private LocalTime closeService;
	

	public TimeService(LocalTime openService, LocalTime closeService) {
		this.openService = openService;
		this.closeService = closeService;
	}

	public boolean isServiceAvailable(LocalTime testTime) {
		return testTime.isAfter(openService) && testTime.isBefore(closeService);
	}
}
