package com.sweetmanor.example.javademo;

import java.io.IOException;

/**
 * Runtime类示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class RuntimeDemo {

	public static void main(String[] args) throws IOException {
		Runtime rt = Runtime.getRuntime();
		System.out.println("处理器数量：" + rt.availableProcessors());
		System.out.println("空闲内存数：" + rt.freeMemory());
		System.out.println("总内存数：" + rt.totalMemory());
		System.out.println("可用最大内存：" + rt.maxMemory());
		rt.exec("notepad.exe");
	}

}
