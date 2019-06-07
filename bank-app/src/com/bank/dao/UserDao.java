package com.bank.dao;

import java.util.List;

import com.bank.beans.User;

//import com.bank.beans.User;

public interface UserDao {

	List<User> getAllUsers();

	void createUser(User user);

	void deleteUser();

	void sortByLastName();
}
