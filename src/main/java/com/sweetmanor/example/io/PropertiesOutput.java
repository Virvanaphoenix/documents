package com.sweetmanor.example.io;

import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesOutput {
	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("user", "yeeku");
		prop.setProperty("password", "123");
		prop.store(new FileOutputStream("user.ini"), null);
	}
}