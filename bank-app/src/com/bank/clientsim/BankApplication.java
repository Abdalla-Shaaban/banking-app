package com.bank.clientsim;

import java.util.Scanner;

import com.bank.controller.*;
import com.bank.beans.User;
import com.bank.dao.impl.*;

public class BankApplication {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("-----------Login------------");
		System.out.println("Enter Username: ");
		String inpUser = keyboard.nextLine();
		System.out.println("Enter Password: ");
		String inpPass = keyboard.nextLine();

		// authenticate user
		LoginController login = new LoginController();
		UserDaoImpl userDao = new UserDaoImpl();
		login.authenticate(inpUser, inpPass);
		if (login.isAuthenticated()) {
			System.out.println("\nWelcome to your banking app.\nYou are logged in as an employee!\n");

			// create new user
			System.out.println("Do you want to create a customer account? (yes/no)");
			if (keyboard.nextLine().equals("yes")) {
				User user = new User();
				System.out.println("Enter username: ");
				user.setUsername(keyboard.nextLine());
				System.out.println("Enter password: ");
				user.setPassword(keyboard.nextLine());
				System.out.println("Enter first name: ");
				user.setFirstName(keyboard.nextLine());
				System.out.println("Enter last name: ");
				user.setLastName(keyboard.nextLine());
				System.out.println("Enter role: ");
				user.setRole(keyboard.nextLine());
				userDao.createUser(user);
				System.out.println("User added to the system!");
			}
			//for lab 3
			System.out.println("Do you want to sort accounts by last name? (yes/no)");
			if (keyboard.nextLine().equals("yes")) {
				userDao.sortByLastName();
			}
			
		}
	}
}
