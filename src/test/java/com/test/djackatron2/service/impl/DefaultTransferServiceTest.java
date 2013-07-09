package com.test.djackatron2.service.impl;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.model.TransferReceipt;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;

/**
 * @author piya.tae@gmail.com
 *
 */
public class DefaultTransferServiceTest {
	
	@Test
	public void testTransfer() {
		//given
		double transferAmount = 30.00;
		double flatFee = 5.00;
		Long srcAcctId = 1l;
		Account srcAccount = new Account();
		srcAccount.setId(srcAcctId);
		srcAccount.setBalance(100.00);
		
		Long destAcctId = 2l;
		Account destAccount = new Account();
		destAccount.setId(destAcctId);
		destAccount.setBalance(0d);
		
		AccountRepository accountRepositoryMock = mock(AccountRepository.class);
		when(accountRepositoryMock.findById(srcAcctId)).thenReturn(srcAccount);
		when(accountRepositoryMock.findById(destAcctId)).thenReturn(destAccount);
		
		FeePolicy feePolicyMock = mock(FeePolicy.class);
		when(feePolicyMock.calculateFee(transferAmount)).thenReturn(flatFee);
		
		DefaultTransferService transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepositoryMock);
		transferService.setFeePolicy(feePolicyMock);
		
		//when
		TransferReceipt receipt = transferService.transfer(transferAmount, srcAcctId, destAcctId);
		
		//then
		assertThat(receipt.getTransferAmount(), equalTo(transferAmount));
		assertThat(receipt.getBlance(), equalTo(65.00));
		assertThat(receipt.getFeeAmount(), equalTo(flatFee));
		assertThat(receipt.getSourceAccount(), equalTo(srcAcctId));
		assertThat(receipt.getDestinationAccount(), equalTo(destAcctId));
		assertNotNull(receipt.getIssueDate());

		assertThat(srcAccount.getBalance(), equalTo(65.00));
		assertThat(destAccount.getBalance(), equalTo(30.00));
	}

}
