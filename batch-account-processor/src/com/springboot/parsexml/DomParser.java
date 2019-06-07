package com.springboot.parsexml;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springboot.watchservice.*;


public class DomParser implements Runnable{
	public void run() {
		MonitorDir newFile = new MonitorDir();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Ninjas8741");
			System.out.println("Connected to " + con.getSchema());
			Statement st = con.createStatement();
			String path = ("C:\\Java\\bank-accounts\\" + newFile.getPath());
			System.out.println(path);
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Account");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					st.executeUpdate("INSERT INTO account " + "VALUES (ACCOUNT_SEQ.nextval,'"
							+ eElement.getElementsByTagName("AccID").item(0).getTextContent() + "','"
							+ eElement.getElementsByTagName("AccNum").item(0).getTextContent() + "','"
							+ eElement.getElementsByTagName("AccType").item(0).getTextContent() + "')");
					System.out.println("account added to database");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
