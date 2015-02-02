package com.sweetmanor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * JDBC工具类:只测试了Oracle数据库
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JDBCUtil {
	private String driver;
	private String url;
	private String user;
	private String pass;
	private Connection conn;

	public JDBCUtil() {
	}

	public JDBCUtil(String driver, String url, String user, String pass) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void connection() {
		conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "没有找到合适的数据库驱动");
			return;
		}
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"数据库连接失败！\n失败信息：" + e.getMessage());
			return;
		}
	}

	public void unConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		if (conn == null)
			connection();
		return conn;
	}

	public void execute(String sql) {
		if (conn == null)
			connection();
		if (conn != null) {
			Statement stat = null;
			try {
				stat = conn.createStatement();
				stat.execute(sql);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"语句执行失败！\n失败信息：" + e.getMessage());
			}
		}
	}

	public void execute(Connection connect, String sql) {
		Statement stat = null;
		try {
			stat = connect.createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"语句执行失败！\n失败信息：" + e.getMessage());
		}
	}

}
