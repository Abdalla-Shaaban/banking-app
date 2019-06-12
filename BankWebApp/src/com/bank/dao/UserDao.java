package com.bank.dao;

import com.bank.beans.User;

//import com.bank.beans.User;

public interface UserDao {
	
	void authenticate(User user);
	
	void createUser(User user);

	void deleteUser();
}
