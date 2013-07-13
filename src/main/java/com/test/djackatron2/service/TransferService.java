package com.test.djackatron2.service;

import java.math.BigDecimal;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountDAO;
import com.test.djackatron2.util.IllegalArguementException;

public class TransferService {
	
	AccountDAO accountDao;
	FeeService feeService;	
	DefaultTimeService timeService;	
	
	
	public void setAccountDao(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}	
	public void setFeeService(FeeService feeService) {
		this.feeService = feeService;
	}
	public void setTimeService(DefaultTimeService timeService) {
		this.timeService = timeService;
	}
	
	public boolean transfer(String accountFrom, String accountTo, BigDecimal transferAmt) {
		if (transferAmt.equals(new BigDecimal(0)))
			throw new IllegalArguementException();
		
		
	
		
		BigDecimal feeAmt = this.feeService.calculateFee(transferAmt);
		
		Account aAccount = this.accountDao.getAccount("a");
		aAccount.setAmount(new BigDecimal(aAccount.getAmount().doubleValue()-transferAmt.doubleValue()-feeAmt.doubleValue()));
		
		Account bAccount = this.accountDao.getAccount("b");
		bAccount.setAmount(new BigDecimal(bAccount.getAmount().doubleValue()+transferAmt.doubleValue()));

		return true;
	}
	
	
	
}
