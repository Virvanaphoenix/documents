package com.sweetmanor.algorithm.number;

/**
 * 变长数组例程：打印杨辉三角
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class VariableArray {

	public static void main(String[] args) {
		final int NMAX = 10;

		// 分配数组空间，每行的元素数不同
		int[][] odds = new int[NMAX + 1][];
		for (int i = 0; i <= NMAX; i++)
			odds[i] = new int[i + 1];

		// 填充数组元素
		for (int i = 0; i < odds.length; i++)
			for (int j = 0; j < odds[i].length; j++) {
				int lotteryOdds = 1;
				for (int k = 1; k <= j; k++) {
					lotteryOdds = lotteryOdds * (i - k + 1) / k;
				}

				odds[i][j] = lotteryOdds;
			}

		// 打印结果
		for (int[] row : odds) {
			for (int odd : row)
				System.out.printf("%4d", odd);
			System.out.println();
		}
	}

}
