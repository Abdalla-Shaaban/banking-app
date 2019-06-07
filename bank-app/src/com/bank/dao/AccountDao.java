package com.bank.dao;

import java.util.List;

import com.bank.beans.Account;

public interface AccountDao {
	
	List<Account> getAllAccounts();

	void createAcc(Account account);
	
	void createAcc();

	void deleteUser();
}
