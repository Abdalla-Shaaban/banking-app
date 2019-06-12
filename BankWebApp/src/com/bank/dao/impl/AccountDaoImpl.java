package com.bank.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bank.beans.Account;
import com.bank.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
	
	List<Account> accountList = new ArrayList<Account>();
	
	public List<Account> getAllAccounts() {
		return accountList;
	}

	public void createAcc(Account account) {

	}
	
	public void createAcc() {
		
	}

	public void deleteUser() {

	}
}
