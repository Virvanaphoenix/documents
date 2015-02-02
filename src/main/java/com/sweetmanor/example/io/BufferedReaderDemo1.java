package com.sweetmanor.example.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderDemo1 {

	public static void main(String[] args) {
		BufferedReader br = null; // 定义缓冲包装流
		try {
			InputStreamReader reader = new InputStreamReader(System.in);// 系统标准输入流为字节流，用转换流将其包装成字符流
			br = new BufferedReader(reader);// 将字符流包装成缓冲流
			String buffer = null;
			// 每次读取一行内容
			while ((buffer = br.readLine()) != null) {
				if (buffer.equals("exit")) {
					System.exit(0);
				}
				System.out.println("输入内容为：\t" + buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭处理流
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
