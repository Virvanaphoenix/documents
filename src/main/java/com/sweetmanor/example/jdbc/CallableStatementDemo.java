package com.sweetmanor.example.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import com.sweetmanor.common.CommonParam;

public class CallableStatementDemo {
	public String driver;
	public String url;
	public String user;
	public String password;

	public void initParam(String filePath) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(filePath));
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");

	}

	public void callProcedure() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			CallableStatement cs = conn.prepareCall("{call add_pro(?,?,?)}");
			cs.setInt(1, 4);
			cs.setInt(2, 5);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			System.out.println("执行结果是" + cs.getInt(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			ClassNotFoundException, IOException, SQLException {
		CallableStatementDemo ct = new CallableStatementDemo();
		ct.initParam(CommonParam.resourcePath + "oracle.ini");
		ct.callProcedure();
	}
}
