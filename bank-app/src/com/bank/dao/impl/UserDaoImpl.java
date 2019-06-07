package com.bank.dao.impl;

import com.bank.beans.User;
import com.bank.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserDaoImpl implements UserDao {

	List<User> userList = new ArrayList<User>();

	public UserDaoImpl() {
		try {
			// using database persistence
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Ninjas8741");
			// System.out.println("Connected to " + con.getSchema());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users");
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String role = rs.getString("role");
				userList.add(new User(username, password, firstName, lastName, role));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * using local file persistence try (BufferedReader br = new BufferedReader(new
		 * FileReader("C:\\Java\\bank-app\\UserList"))) {
		 * 
		 * String sCurrentLine;
		 * 
		 * while ((sCurrentLine = br.readLine()) != null) { String[] user =
		 * sCurrentLine.split(":"); userList.add(new
		 * User(user[0],user[1],user[2],user[3],user[4])); }
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
	}

	public List<User> getAllUsers() {
		return userList;
	}

	public void createUser(User user) {
		try {
			// using database persistence
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Ninjas8741");
			// System.out.println("Connected to " + con.getSchema());
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO users " + " VALUES ('" + user.getUsername() + "','" + user.getPassword()
					+ "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getRole() + "')");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * creating user by using local file persistence String temp = ("\n" +
		 * user.getUsername() + ":" + user.getPassword() + ":" + user.getFirstName() +
		 * ":"
		 * 
		 * + user.getLastName() + ":" + user.getRole()); try (FileWriter fw = new
		 * FileWriter("C:\\Java\\bank-app\\UserList", true)) { fw.write(temp); } catch
		 * (IOException ex) { ex.printStackTrace(); }
		 */
	}

	public void deleteUser() {

	}

	// for lab 3
	public void sortByLastName() {
		Comparator<User> userNameComparator = Comparator.comparing(User::getLastName);
		Collections.sort(userList, userNameComparator);
		userList.forEach(System.out::println);

	}

}
