package com.springboot.batch;

import com.springboot.parser.DomParser;
import com.springboot.watchservice.MonitorDir;

public class BatchAccounts {

	public static void main(String[] args) {

		Thread monitorDir = new Thread(new MonitorDir());
		monitorDir.start();
		Thread parseXML = new Thread(new DomParser());
		parseXML.start();

	}
}
