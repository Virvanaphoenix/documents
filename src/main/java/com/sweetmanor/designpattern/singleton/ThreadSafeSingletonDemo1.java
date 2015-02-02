package com.sweetmanor.designpattern.singleton;

/**
 * 使用同步方法来确保线程安全。此实现的优点是简单、清晰。缺点是在频繁访问的情况下会严重影响性能（HeadFirst设计模式中提到会降低100倍性能）
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ThreadSafeSingletonDemo1 {
	private static ThreadSafeSingletonDemo1 instance; // 使用一个类变量来进行自缓存

	// 使用private修饰构造器，限制自由创建Singleton实例
	private ThreadSafeSingletonDemo1() {
	};

	// 创建一个获取实例的类方法，因为调用此方法前还没有创建实例，所以必须是类方法。
	// 同步方法，确保线程安全
	public static synchronized ThreadSafeSingletonDemo1 getInstance() {
		// 如果为null，才重新创建，否则直接返回实例，确保了只会生成一个实例对象
		if (instance == null)
			instance = new ThreadSafeSingletonDemo1();

		return instance;
	}

}
