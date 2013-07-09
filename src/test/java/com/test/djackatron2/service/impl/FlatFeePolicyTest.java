package com.test.djackatron2.service.impl;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

/**
 * @author piya.tae@gmail.com
 *
 */
public class FlatFeePolicyTest {
	
	@Test
	public void testCalculateFee() {
		//given
		double flatFee = 5.00d;
		FlatFeePolicy feePolicy = new FlatFeePolicy(flatFee);
		
		//when - then
		assertThat(feePolicy.calculateFee(1), equalTo(flatFee));
		assertThat(feePolicy.calculateFee(10), equalTo(flatFee));
		assertThat(feePolicy.calculateFee(1000), equalTo(flatFee));
	}
}
