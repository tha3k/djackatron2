package com.test.djackatron2.service.impl;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import com.test.djackatron2.service.FeePolicy;

/**
 * @author piya.tae@gmail.com
 * 
 */
public class VariableFeePolicyTest {
	@Test
	public void testVariableFeePolicy() {
		//given
		FeePolicy feePolicy = new VariableFeePolicy(1000d, 1000000d, 1d, 20000d);
		
		//when-then
		// 1,000,001 up flat rate 2,0000
		assertThat(feePolicy.calculateFee(1000001d), equalTo(20000d));
		// 1,001 - 1,000,000 percent 1%
		assertThat(feePolicy.calculateFee(1000000d), equalTo(10000d));
		assertThat(feePolicy.calculateFee(1001d), equalTo(10.01d));
		// 1000 down free 0
		assertThat(feePolicy.calculateFee(1000d), equalTo(0d));
	}
}
