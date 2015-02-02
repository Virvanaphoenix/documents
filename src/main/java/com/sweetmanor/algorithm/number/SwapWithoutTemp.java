package com.sweetmanor.algorithm.number;

/**
 * 不使用中间变量，实现两个整数的值交换
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SwapWithoutTemp {

	public static void main(String[] args) {
		int a = 5, b = 3;

		System.out.println("交换之前：");
		System.out.println("a = " + a + "\t b = " + b);

		// 按位异或进行交换
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println("交换之后：");
		System.out.println("a = " + a + "\t b = " + b);

		// 求和进行交换
		a = a + b;
		b = a - b;
		a = a - b;

		System.out.println("两次交换之后：");
		System.out.println("a = " + a + "\t b = " + b);
	}

}
