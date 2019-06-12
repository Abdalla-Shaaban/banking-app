package com.springboot.parser;

import java.io.File;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bootspring.jdbc.ConnectDB;

public class DomParser implements Runnable {

	public DomParser(Path path) {
		try {

			String file = ("C:\\Java\\bank-accounts\\" + path);

			File fXmlFile = new File(file);
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

	public void run() {
		try{Thread.sleep(1000);}catch(InterruptedException e){}  
	}
}
