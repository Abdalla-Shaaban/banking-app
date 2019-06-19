package com.bank.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.beans.User;
import com.bank.dao.impl.UserDaoImpl;

@Controller
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
	
	@RequestMapping("/index")
	public String loanForm(Map<String, Object> model) {
		return "index";
	}
}
