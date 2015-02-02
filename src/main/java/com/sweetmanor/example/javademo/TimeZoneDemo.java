package com.sweetmanor.example.javademo;

import java.util.Arrays;
import java.util.TimeZone;

/**
 * TimeZone类示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class TimeZoneDemo {

	public static void main(String[] args) {
		String[] ids = TimeZone.getAvailableIDs();
		System.out.println(Arrays.toString(ids));
		TimeZone my = TimeZone.getDefault();
		System.out.println(my.getID());
		System.out.println(my.getDisplayName());
		System.out.println(TimeZone.getTimeZone("CNT").getDisplayName());
	}

}
