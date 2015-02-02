package com.sweetmanor.algorithm.number;

/**
 * 数学相关的算法示例
 * 
 * @version 1.0 2014-10-02
 * @author ijlhjj
 */
public class MathDemo {

	/**
	 * 计算调和级数：1 + 1/2 + 1/3 ...
	 */
	public static double HarmonicSeries(int num) {
		double sum = 0.0;
		for (int i = 1; i <= num; i++)
			sum += 1.0 / i;
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(HarmonicSeries(20));
	}

}
