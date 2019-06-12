package com.bank.beans;

public class Account {
	private double balance;
	String accountType;
	private int accountNumber;
	String firstname;
	String lastname;

	public Account(String accountType, int accountNumber, double intialBalance, String firstname, String lastname) {
		balance = intialBalance;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.firstname = firstname;
		this.lastname = lastname;

	}

	public void deposit(double depositAmount) {
		balance += depositAmount;
	}

	public boolean withdraw(double withdrawAmount) {
		if (withdrawAmount > balance) {
			System.out.println("Insufficent Funds!!!");
			return false;
		} else {
			balance -= withdrawAmount;
			return true;
		}
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

}
