package com.sweetmanor.common;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 项目中使用的公共参数
 */
public class CommonParam {
	public static String tempPath = "resource/temp/";// 临时输出目录
	public static String resourcePath = "resource/";// 各种资源目录
	public static Map<String, String> params;// 公共参数，延迟加载

	public static Map<String, String> getParameters() {
		if (params == null) {
			Properties props = new Properties();
			params = new HashMap<String, String>();
			try {
				props.load(new FileInputStream("setting.ini"));
				Enumeration<Object> keys = props.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement().toString();
					String value = props.getProperty(key);
					params.put(key, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return params;
	}

}
