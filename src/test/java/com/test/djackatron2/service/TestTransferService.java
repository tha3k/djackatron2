package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountDAO;
import com.test.djackatron2.util.IllegalArguementException;

//@RunWith(value=Parameterized.class)
public class TestTransferService {
	private String accountFrom;
	private String accountTo;
	private BigDecimal transferAmt;
	
	public TestTransferService() {}
	
	public void TestTransferService1(String accountFrom, String accountTo, BigDecimal transferAmt) {
		//super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.transferAmt = transferAmt;
	}

	//@Parameters
	public static List<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] {
				{"a","b",new BigDecimal(1000)},
				{"a","b",new BigDecimal(10)},
				
			});
		
	}
	
	
	public static Account aAccount = new Account();
	public static Account bAccount = new Account();

	@Test
	public void testTransferAmount30fromAtoB() {
	// given
		TransferService transferService = new TransferService();	
		FeeService feeService = new FeeService();	
		
		aAccount.setId("a");
		aAccount.setName("a");
		aAccount.setAmount(new BigDecimal(100));
		
		bAccount.setId("b");
		bAccount.setName("b");
		bAccount.setAmount(new BigDecimal(0));
		
		AccountDAO accountDao = mock(AccountDAO.class);
		when(accountDao.getAccount(aAccount.getId())).thenReturn(aAccount);
		when(accountDao.getAccount(bAccount.getId())).thenReturn(bAccount);
		
		
		
		
	// when
		transferService.setAccountDao(accountDao);
		transferService.setFeeService(feeService);	
		boolean result = transferService.transfer(aAccount.getId(), bAccount.getId(), new BigDecimal(30));		
		
	// then
		assertEquals(true, result);
		assertEquals(new BigDecimal(65), accountDao.getAccount(aAccount.getId()).getAmount());
		assertEquals(new BigDecimal(30), accountDao.getAccount(bAccount.getId()).getAmount());		

	}
	
	@Test(expected=IllegalArguementException.class)
	public void testTransferAmount0fromAtoB() {
	// given		
		TransferService transferService = new TransferService();	
				
	// when
		transferService.transfer("a", "b", new BigDecimal(0));		
	
	// then
		fail();
	}
	
	
	
	
	
}
