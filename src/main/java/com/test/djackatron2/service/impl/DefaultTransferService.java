package com.test.djackatron2.service.impl;

import java.util.Date;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.model.TransferReceipt;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.TransferService;

/**
 * @author piya.tae@gmail.com
 *
 */
public class DefaultTransferService implements TransferService {
	private AccountRepository accountRepository;
	private FeePolicy feePolicy;

	@Override
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	@Override
	public TransferReceipt transfer(double amount, long srcAcctId, long destAcctId) {
		Account srcAccount = accountRepository.findById(srcAcctId);
		Account destAccount = accountRepository.findById(destAcctId);
		
		double srcBlance = srcAccount.getBalance();
		double destBlance = destAccount.getBalance();
		double feeAmount = feePolicy.calculateFee(amount);
		
		srcBlance -= amount+feeAmount;
		destBlance += amount;
		
		srcAccount.setBalance(srcBlance);
		destAccount.setBalance(destBlance);
		
		TransferReceipt receipt = new TransferReceipt();
		receipt.setSourceAccount(srcAcctId);
		receipt.setDestinationAccount(destAcctId);
		receipt.setTransferAmount(amount);
		receipt.setIssueDate(new Date());
		receipt.setBlance(srcBlance);
		receipt.setFeeAmount(feeAmount);
		
		return receipt;
	}

}
