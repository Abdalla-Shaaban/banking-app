package com.springboot.parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootspring.jdbc.ConnectDB;
import com.springboot.watchservice.MonitorDir;

public class DomParser implements Runnable {
	public DomParser() {

	}

	public void run() {
		MonitorDir newFile = new MonitorDir();
		try {

			String path = ("C:\\Java\\bank-accounts\\" + newFile.getPath());

			File fXmlFile = new File("C:\\Java\\bank-accounts\\acc1.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Account");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String accID = eElement.getElementsByTagName("AccID").item(0).getTextContent();
					String accNum = eElement.getElementsByTagName("AccNum").item(0).getTextContent();
					String accType = eElement.getElementsByTagName("AccType").item(0).getTextContent();
					
					ConnectDB insertToDB = new ConnectDB();
					insertToDB.insertToTable(accID, accNum, accType);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
