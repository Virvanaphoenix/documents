package com.sweetmanor.demo;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/**
 * 采用文件独占锁方式，限制只能运行一个程序实例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class UniqueInstance {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 获取文件独占锁
		FileLock lck = new FileOutputStream(
				System.getProperty("java.io.tmpdir") + "flagFile").getChannel()
				.tryLock();
		// 如果加锁不成功，则异常退出程序
		if (lck == null) {
			System.out.println("已有一个程序实例在运行，本程序将自动退出");
			System.exit(1);
		}
		System.out.println("程序已启动");

		// 下面执行程序的正常步骤
		Thread.sleep(60000);// 为测试让本线程休眠1分钟后退出
	}

}
