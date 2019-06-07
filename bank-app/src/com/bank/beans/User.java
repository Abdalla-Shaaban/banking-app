package com.bank.beans;

public class User {

	private String role, username, password, firstName, lastName;
	
	public User() {
	}
	
	public User(String username, String password, String firstName, String lastName, String role) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	/*public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}*/

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return "Last name: " + getLastName();
	}

}
