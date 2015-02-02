package com.sweetmanor.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期时间工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class DateUtil {
	// 星期常量
	public static final int SUNDAY = 1;
	public static final int MONDAY = 2;
	public static final int TUESDAY = 3;
	public static final int WEDNESDAY = 4;
	public static final int THURSDAY = 5;
	public static final int FRIDAY = 6;
	public static final int SATURDAY = 7;

	/**
	 * 将日期格式化为字符串，格式化参数为：yyyy-MM-dd HH:mm:ss
	 */
	public static String format(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将日期格式化为字符串
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 增加日期
	 */
	public static Date addDay(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	/**
	 * 增加月份
	 */
	public static Date addMonth(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 增加星期
	 */
	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * 设置日期：不改变时间。例如要获取当月第一天，amount为1
	 */
	public static Date setDays(Date date, int amount) {
		return DateUtils.setDays(date, amount);
	}

	/**
	 * 设置小时
	 */
	public static Date setHours(Date date, int amount) {
		return DateUtils.setHours(date, amount);
	}

	/**
	 * 设置分钟
	 */
	public static Date setMinutes(Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	/**
	 * 设置秒
	 */
	public static Date setSeconds(Date date, int amount) {
		return DateUtils.setSeconds(date, amount);
	}

	/**
	 * 设置为当天的开始时间
	 */
	public static Date setBeginTime(Date date) {
		return truncate(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 设置为当天的结束时间
	 */
	public static Date setEndTime(Date date) {
		Date d = setHours(date, 23);
		d = setMinutes(d, 59);
		d = setSeconds(d, 59);
		return d;
	}

	/**
	 * 获取当月最后一天，不改变时间
	 */
	public static Date getLastDayOfMonth(Date date) {
		Date d = setDays(date, 1);
		d = addMonth(d, 1);
		return addDay(d, -1);
	}

	/**
	 * 获取本周第一天（周日）
	 */
	public static Date getFirstDayOfWeek(Date date) {
		return setDayOfWeek(date, SUNDAY);
	}

	/**
	 * 获取本周最后一天（周六）
	 */
	public static Date getLastDayOfWeek(Date date) {
		return setDayOfWeek(date, SATURDAY);
	}

	/**
	 * 设置为指定的星期几，不改变时间
	 * 
	 * @param dayOfWeek
	 *            设置为指定星期几，可接受以下参数：1(SUNDAY),2(MONDAY),3(TUESDAY),4(WEDNESDAY),5
	 *            ( THURSDAY),6(FRIDAY),7(SATURDAY)
	 * @return
	 */
	public static Date setDayOfWeek(Date date, int dayOfWeek) {
		Calendar cal = DateUtils.toCalendar(date);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek); // 设置日为指定的星期
		return cal.getTime();
	}

	/**
	 * 对日期按指定字段进行截取
	 * 
	 * @param date
	 *            要截取的日期
	 * @param field
	 *            截取字段：使用Calendar的字段值。例如要获取当天的00:00:00，field为Calendar.
	 *            DAY_OF_MONTH
	 * @return
	 */
	public static Date truncate(Date date, int field) {
		return DateUtils.truncate(date, field);
	}

	/**
	 * 转换毫秒值为中文字符串。月一律按30天计算
	 */
	public static String convertMillisToString(long millis) {
		int n = 7; // 最高日期单位的标志位
		long year = 0, month = 0, day = 0, hour = 0, minute = 0;

		long second = millis / 1000; // 计算秒
		long millisecond = millis % 1000;// 计算毫秒
		// 有秒值，计算分
		if (second > 0) {
			minute = second / 60;
			second = second % 60;
		}
		// 有分值，计算小时
		if (minute > 0) {
			hour = minute / 60;
			minute = minute % 60;
			n = 5;
		}
		// 有小时，计算天
		if (hour > 0) {
			day = hour / 24;
			hour = hour % 24;
			n = 4;
		}
		// 有天值，计算月，月不分大小月，一律按30天计算
		if (day > 0) {
			month = day / 30;
			day = day % 30;
			n = 3;
		}
		// 有月值，计算年
		if (month > 0) {
			year = month / 12;
			month = month % 12;
			n = 2;
		}
		// 有年值
		if (year > 0)
			n = 1;

		StringBuilder result = new StringBuilder(); // 返回结果字符串
		// 组织字符串
		switch (n) {
		case 1:
			result.append(year + " 年  ");
		case 2:
			result.append(month + " 月  ");
		case 3:
			result.append(day + " 日  ");
		case 4:
			result.append(hour + " 时  ");
		case 5:
			result.append(minute + " 分  ");
		default:
			result.append(second + " 秒  " + millisecond + " 毫秒");
		}
		return result.toString();
	}

}
