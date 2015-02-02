package com.sweetmanor.example.javademo;

/**
 * instanceof运算符示例
 * 
 * @version 1.0 2014-11-05
 * @author ijlhjj
 */
public class InstanceofDemo {

	public static void main(String[] args) {
		Object obj = "Hello";
		System.out.println(obj instanceof Object);
		System.out.println(obj instanceof String);
		System.out.println(obj instanceof Comparable);

		System.out.println(obj instanceof Math);

		String str = "Hello";
		// String既不是Math类也不是其父类，编译无法通过
		// System.out.println(str instanceof Math);
	}

}
