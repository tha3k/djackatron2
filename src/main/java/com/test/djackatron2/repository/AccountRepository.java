package com.test.djackatron2.repository;

import com.test.djackatron2.model.Account;

/**
 * @author piya.tae@gmail.com
 *
 */
public interface AccountRepository {
	public Account findById(long srcAcctId);
	public void updateBalance(Account dstAcct);
}