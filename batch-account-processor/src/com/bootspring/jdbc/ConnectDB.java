package com.bootspring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	
	public void insertToTable(String accID, String accNum, String accType) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Ninjas8741");
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO account " + "VALUES (ACCOUNT_SEQ.nextval,'"
					+ accID + "','"
					+ accNum + "','"
					+ accType + "')");
			System.out.println("Accounts added to database");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
