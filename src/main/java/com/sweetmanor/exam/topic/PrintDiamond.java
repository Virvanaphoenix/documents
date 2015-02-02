package com.sweetmanor.exam.topic;

//打印菱形，双层for循环的控制
public class PrintDiamond {

	public static void main(String[] args) {
		directPrint();
		forPrint(7);
	}

	public static void directPrint() {
		System.out.println("   *");
		System.out.println("  ***");
		System.out.println(" *****");
		System.out.println("*******");
		System.out.println(" *****");
		System.out.println("  ***");
		System.out.println("   *");
	}

	public static void forPrint(int size) {
		// 打印上半部分
		for (int i = 0; i < size / 2 + 1; i++) {
			// 打印前面的空格
			for (int j = size / 2; j > i; j--) {
				System.out.print(" ");
			}
			// 打印*
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 打印下半部分
		for (int i = 0; i < size / 2; i++) {
			// 打印前面的空格
			for (int j = 0; j < i + 1; j++) {
				System.out.print(" ");
			}
			// 打印*
			for (int j = size - 2 * (i + 1); j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
