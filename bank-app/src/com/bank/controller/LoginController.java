package com.bank.controller;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.impl.UserDaoImpl;

public class LoginController {
	private boolean authenticated = false;

	public void authenticate(String inpUser, String inpPass) {
		UserDao userDao = new UserDaoImpl();
		userDao.getAllUsers();

		for (User user : userDao.getAllUsers()) {
				if (inpUser.equals(user.getUsername()) && inpPass.equals(user.getPassword())) {
					System.out.print("You Have Successfully Logged in!");
					setAuthenticated(true);
					break;
				}		
		}

		if (!authenticated)
			System.out.print("You Have Entered an Incorrect Username or Password!");
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean status) {
		this.authenticated = status;
	}
}
