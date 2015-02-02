package com.sweetmanor.example.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sweetmanor.common.CommonParam;

public class JDBCDemo {
	private String driver;
	private String url;
	private String user;
	private String password;

	// 初始化数据库连接参数
	private void initParam(String fileName) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(fileName));
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
	}

	private void query(String sql) {
		Connection conn = null;
		Statement stat = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			// 获取查询结果的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			// 输出列名称，列下标从1开始
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				System.out.print(rsmd.getColumnName(i + 1) + "\t");
			}
			System.out.println();
			// 循环输出查询结果，格式没有太好的控制
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					System.out.print(rs.getString(i + 1) + "\t");
				}
				System.out.println(rs.getRow());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
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
	}

	public static void main(String[] args) throws Exception {
		JDBCDemo jdbc = new JDBCDemo();
		jdbc.initParam(CommonParam.resourcePath + "oracle.ini");
		jdbc.query("select * from emp");
	}
}
