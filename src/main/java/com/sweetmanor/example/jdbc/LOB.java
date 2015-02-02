package com.sweetmanor.example.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import com.sweetmanor.common.CommonParam;

public class LOB {
	private String driver;
	private String url;
	private String user;
	private String password;

	private Connection conn;
	private PreparedStatement insert;

	private void initParam(String paramFile) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(paramFile));
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
	}

	private void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertImage(String filePath) throws Exception {
		insert = conn.prepareStatement("insert into image values(?,?)");
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		insert.setString(1, id);
		File f = new File(filePath);
		InputStream is = new FileInputStream(f);
		insert.setBinaryStream(2, is, (int) f.length());
		insert.executeUpdate();
	}

	public void close() {
		if (insert != null) {
			try {
				insert.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		LOB jdbc = new LOB();
		jdbc.initParam(CommonParam.resourcePath + "oracle.ini");
		jdbc.connect();
		jdbc.insertImage("d:/Penguins.jpg");
		jdbc.close();
	}
}
