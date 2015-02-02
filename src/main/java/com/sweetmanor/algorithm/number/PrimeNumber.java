package com.sweetmanor.algorithm.number;

/**
 * 素数判断：判断并输出101-200之间的素数
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class PrimeNumber {

	/**
	 * 素数判断方法
	 */
	public static boolean isPrime(int num) {
		if (num < 2)
			return false;

		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0)
				return false;

		return true;
	}

	public static void main(String[] args) {
		int count = 0;

		for (int i = 101; i < 200; i++)
			if (isPrime(i)) {
				System.out.print(i + "\t");
				count++;
			}

		System.out.println("\n素数的个数：" + count);
	}

}
