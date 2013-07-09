package com.test.djackatron2.service.impl;

import java.util.Date;

import org.joda.time.LocalTime;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.model.TransferReceipt;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.InsufficientFundsException;
import com.test.djackatron2.service.OutOfServiceException;
import com.test.djackatron2.service.TimeService;
import com.test.djackatron2.service.TransferService;

/**
 * @author piya.tae@gmail.com
 *
 */
public class DefaultTransferService implements TransferService {
	private AccountRepository accountRepository;
	private FeePolicy feePolicy;
	private double minimumTransferAmount;
	private TimeService timeService;

	@Override
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	@Override
	public void setMinimumTransferAmount(double minimumTransferAmount) {
		this.minimumTransferAmount = minimumTransferAmount;
	}

	@Override
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	@Override
	public TransferReceipt transfer(double amount, long srcAcctId, long destAcctId) {
		if (timeService!=null&&!timeService.isServiceAvailable(new LocalTime())) {
			throw new OutOfServiceException();
		}
		
		if (amount<=0d || amount < minimumTransferAmount) {
			throw new IllegalArgumentException();
		}
		
		Account srcAccount = accountRepository.findById(srcAcctId);
		Account destAccount = accountRepository.findById(destAcctId);
		
		double srcBlance = srcAccount.getBalance();
		double destBlance = destAccount.getBalance();
		double feeAmount = feePolicy.calculateFee(amount);
		
		srcBlance -= amount+feeAmount;
		if (srcBlance>=0) {
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
		} else {
			throw new InsufficientFundsException();
		}
	}

}
