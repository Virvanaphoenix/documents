package com.sweetmanor.algorithm.number;

/**
 * 中奖几率计算：如果从n个数字中抽取k个数字，计算概率公式为： (n*(n-1)*(n-2)...*(n-k+1))/(1*2*3...*k)
 * 例如：从50个数字中抽取6个数字，计算如下： (50*49*48*47*46*45) / (1*2*3*4*5*6)=1/15890700
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class LotteryOdds {

	public static void main(String[] args) {
		int n = 50, k = 6;

		int lotteryOdds = 1;
		for (int i = 1; i <= k; i++)
			lotteryOdds = lotteryOdds * (n - i + 1) / i;

		System.out.println("中奖几率为： 1 / " + lotteryOdds);
	}

}
