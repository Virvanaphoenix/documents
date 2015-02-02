package com.sweetmanor.example.javademo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * GregorianCalendar类判断闰年的方法
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class GregorianCalendarDemo {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println("1900-2100年之间的闰年：");
		for (int i = 1900; i < 2100; i++)
			if (((GregorianCalendar) cal).isLeapYear(i))
				System.out.println(i);
	}

}
