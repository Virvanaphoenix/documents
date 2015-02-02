package com.sweetmanor.algorithm.number;

import java.math.BigDecimal;

/**
 * 计算1 + 1/2! + 1/3! ··· 1/20!
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FactorialSum {

	public static void main(String[] args) {
		BigDecimal sum = new BigDecimal("0.0"); // 结果和
		BigDecimal factorial = new BigDecimal("1.0"); // 阶乘项的计算结果

		int i = 1; // 循环增量
		while (i <= 20) {
			sum = sum.add(factorial); // 累加各项阶乘的和
			++i;
			factorial = factorial.multiply(new BigDecimal(1.0 / i)); // 计算阶乘项
		}

		System.out.println("1 + 1/2! + 1/3! ··· 1/20!的计算结果等于：\n" + sum);
	}

}
