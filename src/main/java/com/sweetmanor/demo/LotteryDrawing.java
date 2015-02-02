package com.sweetmanor.demo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 彩票抽奖示例：例如21选3的抽奖模式
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class LotteryDrawing {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.print("在多少个号码中抽取：");
		int n = in.nextInt();

		System.out.print("抽取几个号码：");
		int k = in.nextInt();

		// 初始化号码池
		int[] numbers = new int[n];
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = i + 1;

		// 随机选取中奖号码
		Random random = new Random();
		int[] result = new int[k];
		for (int i = 0; i < result.length; i++) {
			int r = random.nextInt(n);// 生成随机索引
			result[i] = numbers[r];
			numbers[r] = numbers[n - 1];// 移除已抽取号码
			n--;
		}

		Arrays.sort(result);
		System.out.println("中奖号码为：");
		for (int r : result)
			System.out.println(r);
	}

}
