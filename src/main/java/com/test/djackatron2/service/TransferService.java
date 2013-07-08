package com.test.djackatron2.service;

import com.test.djackatron2.model.TransferReceipt;
import com.test.djackatron2.repository.AccountRepository;

public interface TransferService {
	public TransferReceipt transfer(double amount, long srcAcctId, long destAcctId);
	public void setAccountRepository(AccountRepository accountRepository);
	public void setFeePolicy(FeePolicy feePolicy);
	public void setMinimumTransferAmount(double minimumTransferAmount);
}