package com.sweetmanor.algorithm.number;

/**
 * 斐波那契数列：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class Fibonacci {
	private static int MONTH = 10; // 计算项数，实际为 MONTH * 2 项

	/**
	 * 递归计算斐波那契数列值（没有缓存，效率可能很低）
	 * 
	 * @param index
	 *            在数列中的位置
	 * @return 对应的数值
	 */
	public static long f(long index) {
		if (index == 1 || index == 2)
			return 1;
		else
			return f(index - 1) + f(index - 2);
	}

	public static void main(String[] args) {
		System.out.println("斐波那契数列：");

		// 输出序号行
		System.out.print("序号：\t");
		for (int i = 0; i < MONTH; i++)
			System.out.print((i * 2 + 1) + "\t" + (i * 2 + 2) + "\t");
		System.out.println();

		// 直接计算斐波那契数列
		System.out.print("数列：\t");
		long f1 = 1L;
		long f2 = 1L;
		for (int i = 0; i < MONTH; i++) {
			System.out.print(f1 + "\t" + f2 + "\t");
			f1 = f1 + f2;
			f2 = f1 + f2;
		}
		System.out.println();

		// 递归计算斐波那契数列
		System.out.print("数列：\t");
		for (int i = 1; i <= MONTH * 2; i++)
			System.out.print(f(i) + "\t");

	}

}
