package com.sweetmanor.exam.topic;

import java.util.Scanner;

/*
 * 题目要求：输入一个正整数N（N<10），打印一个N*N的矩形数组，打印结果为由外层到内层的循环打印
 * 
 * 示例： 
 * 	输入：5 
 * 	输出： 
 * 1   2  3  4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * 
 *        测试双层for循环的控制
 */
public class ArrayPrint {
	// 数组矩阵的大小
	private int N;
	private int[][] array;

	public ArrayPrint() {
		N = 5;
		array = new int[N][N];
	}

	public ArrayPrint(int n) {
		N = n;
		array = new int[N][N];
	}

	private void print() {
		// i为行号，j为列号，i0为行旗标，j0为列旗标，count为数组元素赋值，num为需要赋值个数
		int i = 0, j = 0, i0 = 0, j0 = 0, count = 1, num = N - 1;
		while (num > 0) {
			// 上层行赋值
			for (i = i0, j = j0; j < num; j++, count++) {
				array[i][j] = count;
			}
			// 最后列赋值
			for (i = i0; i < num; i++, count++) {
				array[i][j] = count;
			}
			// 下层行赋值
			for (j = num; j > j0; j--, count++) {
				array[i][j] = count;
			}
			// 首列赋值
			for (i = num; i > i0; i--, count++) {
				array[i][j] = count;
			}
			// 起始行加1，起始列加1
			i0++;
			j0++;
			num--;
		}
		// 如果数组大小为奇数，给最中间的元素赋值
		if (N % 2 != 0) {
			array[N / 2][N / 2] = count;
		}
		// 循环打印输出数组元素的值
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		System.out.println("请输入一个正整数N（1<N<10）：");
		Scanner sc = new Scanner(System.in);
		// 获取输入数组的大小
		int arraySize = sc.nextInt();
		// 确保获取的数组大小值在限定的范围内
		while (arraySize >= 10 || arraySize <= 1) {
			System.out.println("请输入正确的值（1<N<10）：");
			arraySize = sc.nextInt();
		}
		ArrayPrint ap = new ArrayPrint(arraySize);
		ap.print();
	}
}
