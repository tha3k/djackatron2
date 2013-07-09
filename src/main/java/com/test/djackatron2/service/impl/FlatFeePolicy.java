package com.test.djackatron2.service.impl;

import com.test.djackatron2.service.FeePolicy;

/**
 * @author piya.tae@gmail.com
 *
 */
public class FlatFeePolicy implements FeePolicy {
	private final double flatFee;

	public FlatFeePolicy(double flatFee) {
		this.flatFee = flatFee;
	}


	@Override
	public double calculateFee(double transferAmount) {
		return flatFee;
	}

}
