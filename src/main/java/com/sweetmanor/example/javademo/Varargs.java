package com.sweetmanor.example.javademo;

/**
 * 形参个数可变的参数示例
 * 
 * @version 1.0 2014-10-24
 * @author ijlhjj
 */
public class Varargs {

	/**
	 * 个数可变形参只能位于参数列表的最后
	 */
	public static void print(String... strings) {
		for (String s : strings)
			System.out.println(s);
	}

	public static void main(String[] args) {
		print("疯狂Java讲义", "疯狂Ajax讲义");// 可使用任意个数的参数
	}

}
