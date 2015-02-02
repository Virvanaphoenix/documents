package com.sweetmanor.algorithm.number;

/**
 * 水仙花数(narcissus number)："水仙花数"是指一个三位数，其各位数字立方和等于该数本身 例如：153 = 1*1*1 ＋ 5*5*5 ＋
 * 3*3*3
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class NarcissisticNumber {

	/**
	 * 水仙花数判断方法
	 */
	public static boolean isNarcissistic(int num) {
		// 不是三位数，返回false
		if (num < 100 || num > 999)
			return false;

		int b = num / 100;// 取百位数
		int s = (num % 100) / 10;// 取十位数
		int g = num % 10;// 取个位数

		if (num == (b * b * b + s * s * s + g * g * g))
			return true;

		return false;
	}

	public static void main(String[] args) {
		System.out.println("水仙花数：");
		for (int i = 100; i < 1000; i++)
			if (isNarcissistic(i))
				System.out.print(i + "\t");
	}

}
