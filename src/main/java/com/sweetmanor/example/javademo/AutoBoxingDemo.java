package com.sweetmanor.example.javademo;

/**
 * 自动装箱示例
 * 
 * @version 1.0 2014-11-05
 * @author ijlhjj
 */
public class AutoBoxingDemo {

	public static void main(String[] args) {
		Integer i1 = 2;
		Integer i2 = 2;
		System.out.println(i1 == i2);// Integer类对-128——127之间的整数进行了缓存

		Integer i3 = 128;
		Integer i4 = 128;
		System.out.println(i3 == i4);
		System.out.println(i3.equals(i4));// 比较两个引用对象是否相等，需要使用equals方法
	}

}
