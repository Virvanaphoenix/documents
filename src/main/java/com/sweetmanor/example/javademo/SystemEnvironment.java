package com.sweetmanor.example.javademo;

import java.util.Properties;
import java.util.Set;

/**
 * 获取所有系统参数
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SystemEnvironment {

	public static void main(String[] args) {
		Properties systemProperty = System.getProperties();
		Set<String> keys = systemProperty.stringPropertyNames();
		for (String prop : keys)
			System.out.println(prop + ":\t" + systemProperty.getProperty(prop));
	}

}
