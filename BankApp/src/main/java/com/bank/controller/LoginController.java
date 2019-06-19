package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bank.beans.User;
import com.bank.dao.impl.UserDaoImpl;

@Controller
@SessionAttributes("username")
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
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "index";
    }

    @RequestMapping(value="/index", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password){

        //boolean isValidUser = service.validateUser(name, password);
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	UserDaoImpl auth = new UserDaoImpl();
    	auth.authenticate(user);
        if (!user.isLoginStatus()) {
            model.put("errorMessage", "Invalid Credentials");
            return "index";
        }

        model.put("username", username);
        model.put("password", password);

        return "welcome";
    }
}
