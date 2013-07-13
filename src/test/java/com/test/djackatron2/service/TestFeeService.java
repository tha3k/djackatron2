package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class TestFeeService {
	
	@Test
	public void testCaculateFeeForAmount1000() {
		FeeService feeService = new FeeService();		
		assertEquals(new BigDecimal(5), feeService.calculateFee(new BigDecimal(1000)));		
	}

	@Test
	public void testCaculateFeeForAmount10() {
		FeeService feeService = new FeeService();		
		assertEquals(new BigDecimal(5), feeService.calculateFee(new BigDecimal(10)));		
	}

	@Test
	public void testCaculateFeeForAmount1() {
		FeeService feeService = new FeeService();		
		assertEquals(new BigDecimal(5), feeService.calculateFee(new BigDecimal(1)));		
	}
}
