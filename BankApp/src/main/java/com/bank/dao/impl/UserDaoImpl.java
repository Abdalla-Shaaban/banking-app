package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.beans.User;
import com.bank.dao.UserDao;

public class UserDaoImpl implements UserDao {

	public void authenticate(User user) {
		try {
			// using database persistence
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			// System.out.println("Connected to " + con.getSchema());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM users WHERE username='" + user.getUsername()
					+ "' and password='" + user.getPassword() + "'");
			if (rs.next()) {
				if (rs.getInt(1) == 1) {
					user.setLoginStatus(true);
				}
			} else {
				user.setLoginStatus(false);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createUser(User user) {
		try {
			// using database persistence
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			// System.out.println("Connected to " + con.getSchema());
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO users " + " VALUES ('" + user.getUsername() + "','" + user.getPassword()
					+ "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getRole() + "')");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser() {

	}

}
