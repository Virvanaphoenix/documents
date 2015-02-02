package com.sweetmanor.example.javademo;

/**
 * 静态方法调用：null值也可以调用类方法
 * 
 * @version 1.0 2014-11-05
 * @author ijlhjj
 */
public class StaticMethodCall {

	public static void test() {
		System.out.println("test类方法被调用");
	}

	public static void main(String[] args) {
		StaticMethodCall ins = null;
		ins.test();// null值也可以调用类方法
	}

}
