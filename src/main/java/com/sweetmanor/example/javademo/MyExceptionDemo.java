package com.sweetmanor.example.javademo;

/**
 * 自定义异常类示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	MyException() {
	};

	MyException(String msg) {
		super(msg);
	};

}

public class MyExceptionDemo {

	public static void firstMethod() throws MyException {
		secondMethod();
	};

	public static void secondMethod() throws MyException {
		treardMethod();
	}

	public static void treardMethod() throws MyException {
		throw new MyException("自定义异常");
	}

	public static void main(String[] args) throws MyException {
		firstMethod();
	}

}
