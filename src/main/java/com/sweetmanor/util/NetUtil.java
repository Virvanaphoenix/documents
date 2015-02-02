package com.sweetmanor.util;

import java.net.InetAddress;

/**
 * 网络工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class NetUtil {
	private static final String HOST = "www.baidu.com";
	private static final int TIMEOUT = 3000;

	/**
	 * 判断指定主机是否可联通方法
	 */
	public static boolean isReachable(String host, int timeout) {
		if (host == null || host.equals(""))
			return false;

		try {
			InetAddress ip = InetAddress.getByName(host);
			return ip.isReachable(timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断指定主机是否可联通方法
	 */
	public static boolean isReachable() {
		return isReachable(HOST, TIMEOUT);
	}

}
