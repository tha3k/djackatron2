package com.test.djackatron2.model;

/**
 * @author piya.tae@gmail.com
 *
 */
public class TransferReceipt {
	private Long sourceAccount;
	private Long destinationAccount;
	private Double transferAmount;
	private Double feeAmount;

	public Long getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Long sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Long getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Long destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

}
