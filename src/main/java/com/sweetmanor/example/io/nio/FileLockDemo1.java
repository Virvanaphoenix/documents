package com.sweetmanor.example.io.nio;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import com.sweetmanor.common.CommonParam;

public class FileLockDemo1 {

	public static void main(String[] args) throws Exception {
		FileChannel channel = new FileInputStream(CommonParam.tempPath
				+ "test.txt").getChannel();// 创建FileChannel
		FileLock lock = channel.tryLock(0, Long.MAX_VALUE, true);// 尝试获取FileLock，非阻塞、全文件、排他锁会报错，共享锁不报
		Thread.sleep(30000);// 线程暂停30秒
		lock.release();// 释放文件锁
	}

}
