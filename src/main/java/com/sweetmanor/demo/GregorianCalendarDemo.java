package com.sweetmanor.demo;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

/**
 * 格林威治日历示例：显示阳历的实用算法，其中日期的增减值得借鉴
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class GregorianCalendarDemo {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);// 示例使用美国日期格式

		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(DAY_OF_MONTH);
		int month = calendar.get(MONTH);
		int firstDayOfWeek = calendar.getFirstDayOfWeek();// 星期的起始日，中国和美国都是星期天

		calendar.set(DAY_OF_MONTH, 1);
		int weekday = calendar.get(DAY_OF_WEEK);// 当月1日的星期

		int indent = 0;// 当月1日的缩进
		while (weekday != firstDayOfWeek) {
			indent++;
			calendar.add(DAY_OF_MONTH, -1);
			weekday = calendar.get(DAY_OF_WEEK);
		}

		// 输出星期的短名称
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();// 星期的短格式名称
		do {
			System.out.printf("%4s", weekdayNames[weekday]);
			calendar.add(DAY_OF_MONTH, 1);
			weekday = calendar.get(DAY_OF_WEEK);
		} while (weekday != firstDayOfWeek);
		System.out.println();

		// 第一行日期的缩进
		for (int i = 1; i <= indent; i++)
			System.out.print("    ");

		calendar.set(DAY_OF_MONTH, 1);
		do {
			// 输出日期
			int day = calendar.get(DAY_OF_MONTH);
			System.out.printf("%3d", day);

			// 当日加一个标记*
			if (day == today)
				System.out.print("*");
			else
				System.out.print(" ");

			calendar.add(DAY_OF_MONTH, 1);
			weekday = calendar.get(DAY_OF_WEEK);

			if (weekday == firstDayOfWeek)// 星期第一天换行
				System.out.println();
		} while (calendar.get(MONTH) == month);// 循环直到下月

		if (weekday != firstDayOfWeek)// 最后加一个换行，不是必须的
			System.out.println();
	}

}
