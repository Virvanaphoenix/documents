package com.sweetmanor.designpattern.singleton;

/**
 * 在类加载时即初始化类实例。此实现的优点是简单，并绝对保证了只有一个实例对象。缺点是不管是否使用都会初始化一个对象， 不存在延迟加载的优势
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ThreadSafeSingletonDemo2 {
	private static ThreadSafeSingletonDemo2 instance = new ThreadSafeSingletonDemo2(); // 使用一个类变量来进行自缓存，在类加载时进行初始化

	// 使用private修饰构造器，限制自由创建Singleton实例
	private ThreadSafeSingletonDemo2() {
	};

	// 创建一个获取实例的类方法，因为调用此方法前还没有创建实例，所以必须是类方法
	public static ThreadSafeSingletonDemo2 getInstance() {
		return instance;
	}

}
