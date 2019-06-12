package com.bank.controller;

import com.bank.beans.User;
import com.bank.dao.impl.UserDaoImpl;

public class LoginController {

	public boolean authenticate(User user) {
		UserDaoImpl auth = new UserDaoImpl();
		auth.authenticate(user);
		if (user.isLoginStatus()) {
			return true;
		} else {
			return false;
		}
	}
}
