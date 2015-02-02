package com.sweetmanor.common;

import java.util.Random;

/**
 * 随机整型数组生成器
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class RandomInt {
	private static int max = 100; // 生成的最大随机数（不包括），默认生成小于100的整数

	/**
	 * @param max
	 *            生成随机数的最大值
	 */
	public static void setMax(int max) {
		RandomInt.max = max;
	}

	/**
	 * 生成包含10个元素的随机数组
	 */
	public static int[] random() {
		return random(10);
	}

	/**
	 * 生成包含count个元素的随机数组
	 */
	public static int[] random(int count) {
		if (count < 1)
			return null;

		int[] intArray = new int[count];
		Random random = new Random();
		for (int i = 0; i < count; i++)
			intArray[i] = random.nextInt(max);// 对每个元素随机赋值

		return intArray;
	}

}
