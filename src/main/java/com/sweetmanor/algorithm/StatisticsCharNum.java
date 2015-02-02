package com.sweetmanor.algorithm;

import java.util.Scanner;

/**
 * 输入一行字符，分别统计其中英文字母、空格、数字和其它字符的个数。 Java中使用UTF-16描述CHAR类型 汉字的判断使用[\u4e00-\u9fa5]
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class StatisticsCharNum {

	// 统计字符串中的英文字母、空格、数字和其它字符的个数
	// 如果输入中文，当其他字符处理
	public static void countCharNum(String text) {
		int cNum = 0; // 英文字符个数
		int sNum = 0;// 空格个数
		int nNum = 0;// 数字个数
		int oNum = 0;// 其他字符个数

		char[] cArr = text.toCharArray();
		for (int i = 0; i < cArr.length; i++) {
			char c = cArr[i];
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
				cNum++;
			else if (c == ' ')
				sNum++;
			else if (c >= '0' && c <= '9')
				nNum++;
			else
				oNum++;
		}

		System.out.println("字符串中包含：英文字符" + cNum + "个，空格" + sNum + "个，数字" + nNum
				+ "个，其他字符" + oNum + "个");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String text = null;
		while ((text = sc.nextLine()) != null) {
			if ("exit".equals(text))
				System.exit(0);
			countCharNum(text);// 统计字符数
		}
		sc.close();
	}

}
