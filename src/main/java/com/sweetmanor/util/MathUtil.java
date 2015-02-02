package com.sweetmanor.util;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数学计算工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class MathUtil {

	/**
	 * 求最大公约数函数。前置条件：p、q是大于0的正整数
	 */
	public static int gcd(int m, int n) {
		if (m % n == 0)
			return n;
		else
			return gcd(n, m % n);
	}

	/**
	 * 找出数组中最大元素
	 */
	public static int max(int[] a) {
		return NumberUtils.max(a);
	}

	/**
	 * 计算数组元素的平均值
	 */
	public static double avg(int[] a) {
		int N = a.length;
		double sum = 0.0;
		for (int i = 0; i < N; i++)
			sum += a[i];
		return sum / N;
	}

	/**
	 * 复制数组
	 */
	public static void copyArray(int[] fromArray, int[] toArray) {
		int N = fromArray.length;
		for (int i = 0; i < N; i++)
			toArray[i] = fromArray[i];
	}

	/**
	 * 反转数组元素
	 */
	public static void reversalArray(int[] a) {
		int N = a.length;
		for (int i = 0; i < N / 2; i++) {
			int temp = a[i];
			a[i] = a[N - 1 - i];
			a[N - 1 - i] = temp;
		}
	}

	/**
	 * 矩阵相乘（方阵：行列相同）
	 */
	public static int[][] matrixMultiply(int[][] a, int[][] b) {
		int N = a.length;
		int[][] c = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < N; k++)
					c[i][j] += a[i][k] * b[k][j];
		return c;
	}

	/**
	 * 素数判断方法
	 */
	public static boolean isPrime(int n) {
		// 小于2的数直接返回false
		if (n < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++)
			if (n % i == 0)
				return false;
		return true;
	}

	/**
	 * 牛顿迭代法求平方根
	 */
	public static double sqrt(double c) {
		if (c < 0)
			return Double.NaN;
		double err = 1e-15;
		double t = c;
		while (Math.abs(t - c / t) > err * t)
			t = (c / t + t) / 2.0;
		return t;
	}

}
