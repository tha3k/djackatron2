package com.test.djackatron2.service.impl;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.model.TransferReceipt;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.InsufficientFundsException;
import com.test.djackatron2.service.OutOfServiceException;
import com.test.djackatron2.service.TimeService;

/**
 * @author piya.tae@gmail.com
 *
 */
public class DefaultTransferServiceTest {
	private Account srcAccount;
	private Account destAccount;
	private DefaultTransferService transferService;
	
	@Before
	public void setup() {
		double flatFee = 5.00;
		double minimumTransferAmount = 10.00;
		Long srcAcctId = 1l;
		Long destAcctId = 2l;
		srcAccount = new Account();
		srcAccount.setId(srcAcctId);
		srcAccount.setBalance(100.00);
		
		destAccount = new Account();
		destAccount.setId(destAcctId);
		destAccount.setBalance(0d);
		
		AccountRepository accountRepositoryMock = mock(AccountRepository.class);
		when(accountRepositoryMock.findById(srcAcctId)).thenReturn(srcAccount);
		when(accountRepositoryMock.findById(destAcctId)).thenReturn(destAccount);
		
		FeePolicy feePolicyMock = mock(FeePolicy.class);
		when(feePolicyMock.calculateFee(anyDouble())).thenReturn(flatFee);
		
		transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepositoryMock);
		transferService.setFeePolicy(feePolicyMock);
		transferService.setMinimumTransferAmount(minimumTransferAmount);
	}
	
	@Test
	public void testTransfer() {
		//given
		double transferAmount = 30.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);
		
		//when
		TransferReceipt receipt = transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
		assertThat(receipt.getTransferAmount(), equalTo(transferAmount));
		assertThat(receipt.getBlance(), equalTo(65.00));
		assertThat(receipt.getFeeAmount(), equalTo(5.00));
		assertThat(receipt.getSourceAccount(), equalTo(srcAccount.getId()));
		assertThat(receipt.getDestinationAccount(), equalTo(destAccount.getId()));
		assertNotNull(receipt.getIssueDate());

		assertThat(srcAccount.getBalance(), equalTo(65.00));
		assertThat(destAccount.getBalance(), equalTo(30.00));
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void testTransferWhenInsufficientAmount() {
		//given
		double transferAmount = 110.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);
		
		//when
		transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenZeroAmount() {
		//given
		double transferAmount = 0d;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);
		
		//when
		transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
		fail();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenNegativeAmount() {
		//given
		double transferAmount = -10.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);
		
		//when
		transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenTransferLessThenMinimumAmount() {
		//given
		double transferAmount = 5.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);
		
		//when
		transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
		fail();
	}

    @Test
    public void testTransferWithCheckTimeService() {
		//given
		double transferAmount = 30.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);

		TimeService mockTimeService = mock(TimeService.class);
        when(mockTimeService.isServiceAvailable(any(LocalTime.class))).thenReturn(true);
        transferService.setTimeService(mockTimeService);
		
		//when
		TransferReceipt receipt = transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
		
		//then
    	//verify behavior
		verify(mockTimeService).isServiceAvailable(any(LocalTime.class));
		
		assertThat(receipt.getTransferAmount(), equalTo(transferAmount));
		assertThat(receipt.getBlance(), equalTo(65.00));
		assertThat(receipt.getFeeAmount(), equalTo(5.00));
		assertThat(receipt.getSourceAccount(), equalTo(srcAccount.getId()));
		assertThat(receipt.getDestinationAccount(), equalTo(destAccount.getId()));
		assertNotNull(receipt.getIssueDate());

		assertThat(srcAccount.getBalance(), equalTo(65.00));
		assertThat(destAccount.getBalance(), equalTo(30.00));
    }

    @Test
    public void testTransferWithCheckingOutOfTimeService() {
    	//given
		double transferAmount = 30.00;
		assertEquals(100.00, srcAccount.getBalance(), 0);
		assertEquals(0d, destAccount.getBalance(), 0);

		TimeService mockTimeService = mock(TimeService.class);
        when(mockTimeService.isServiceAvailable(any(LocalTime.class))).thenReturn(false);
        transferService.setTimeService(mockTimeService);

        //when
        try {
    		transferService.transfer(transferAmount, srcAccount.getId(), destAccount.getId());
        	fail();
        } catch (OutOfServiceException e) {
        	//then
        	//verify behavior
            verify(mockTimeService).isServiceAvailable(any(LocalTime.class));
		}

    }
}
