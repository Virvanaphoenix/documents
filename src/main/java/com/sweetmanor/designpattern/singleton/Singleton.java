package com.sweetmanor.designpattern.singleton;

/**
 * 经典单例类，非线程安全
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class Singleton {
	private static Singleton instance; // 使用一个类变量来进行自缓存

	// 使用private修饰构造器，限制自由创建Singleton实例
	private Singleton() {
	};

	// 创建一个获取实例的类方法，因为调用此方法前还没有创建实例，所以必须是类方法
	public static Singleton getInstance() {
		// 如果为null，才重新创建，否则直接返回实例，确保了只会生成一个实例对象
		if (instance == null)
			instance = new Singleton();

		return instance;
	}

}
