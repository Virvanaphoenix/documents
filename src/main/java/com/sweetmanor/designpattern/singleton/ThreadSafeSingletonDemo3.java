package com.sweetmanor.designpattern.singleton;

/**
 * 使用双重检查加锁来确保线程安全。此实现的优点是最大限度的降低了对性能的影响，并且是最合理的解决方案。缺点是实现相对复杂。
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ThreadSafeSingletonDemo3 {
	private static volatile ThreadSafeSingletonDemo3 instance;// 使用一个类变量来进行自缓存，双重检查加锁，JDK1.5以后版本支持

	// 使用private修饰构造器，限制自由创建Singleton实例
	private ThreadSafeSingletonDemo3() {
	};

	// 创建一个获取实例的类方法，因为调用此方法前还没有创建实例，所以必须是类方法
	public static ThreadSafeSingletonDemo3 getInstance() {
		// 如果为null，才重新创建，否则直接返回实例，确保了只会生成一个实例对象
		if (instance == null) {
			// 需要创建实例时进行加锁，保证了只在第一次加锁，其他时候不会锁定，所以对性能的影响很小
			synchronized (ThreadSafeSingletonDemo3.class) {
				// 同步锁后需要再次判断对象是否为null，这就是为什么叫双重检查加锁
				if (instance == null)
					instance = new ThreadSafeSingletonDemo3();
			}
		}
		return instance;
	}

}
