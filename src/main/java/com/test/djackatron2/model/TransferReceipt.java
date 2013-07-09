package com.test.djackatron2.model;

import java.util.Date;

/**
 * @author piya.tae@gmail.com
 *
 */
public class TransferReceipt {
	private Long sourceAccount;
	private Long destinationAccount;
	private Double transferAmount;
	private Double feeAmount;
	private Double blance;
	private Date issueDate;

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

	public Double getBlance() {
		return blance;
	}

	public void setBlance(Double blance) {
		this.blance = blance;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((destinationAccount == null) ? 0 : destinationAccount
						.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result
				+ ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferReceipt other = (TransferReceipt) obj;
		if (destinationAccount == null) {
			if (other.destinationAccount != null)
				return false;
		} else if (!destinationAccount.equals(other.destinationAccount))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (sourceAccount == null) {
			if (other.sourceAccount != null)
				return false;
		} else if (!sourceAccount.equals(other.sourceAccount))
			return false;
		return true;
	}

}
