package com.sweetmanor.algorithm.number;

import java.util.ArrayList;
import java.util.List;

/**
 * 分解质因子：将一个正整数分解质因子。 例如：输入90,打印出 90 = 2 * 3 * 3 * 5。
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class PrimeFactor {

	/**
	 * 分解质因数方法
	 * 
	 * 对n分解质因数，步骤如下： (1)如果n为质数，则分解完成; (2)如果n能被素数k整除，则k是一个质因子。n=n/k，转到步骤1;
	 * (3)如果n不能被k整除，k=k+1，转到步骤1。
	 * 
	 * @param num
	 *            待分解质因子的数
	 * @param result
	 *            存放分解结果，必须在调用前初始化
	 */
	public static void primeFactor(int num, List<Integer> result) {
		if (PrimeNumber.isPrime(num)) {
			result.add(num); // num为素数，分解完成
		} else {
			// 在可能的质因子范围内循环
			for (int i = 2; i <= Math.sqrt(num); i++)
				// 是素数，能整除，则找到一个质因子
				if (PrimeNumber.isPrime(i) && (num % i == 0)) {
					result.add(i);
					primeFactor(num / i, result);// 递归分解质因子
					break;// 找到最小的质因子后，不需要再循环其余的值
				}
		}
	}

	public static void main(String[] args) {
		System.out.println("分解质因子：");
		List<Integer> primeFactorList = new ArrayList<Integer>();
		primeFactor(90, primeFactorList);// 调用分解质因子方法
		System.out.println(primeFactorList);
	}

}
