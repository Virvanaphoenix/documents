package com.sweetmanor.example.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo1 {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(
				"src/com/sweetmanor/example/io/RandomAccessFileDemo1.java", "r");// 以只读方式打开文件
		System.out.println("文件指针的初始位置：" + raf.getFilePointer());// 获取初始文件指针位置，初始位置为0
		raf.seek(300);// 移动文件指针位置
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		while ((hasRead = raf.read(buffer)) > 0) {
			System.out.print(new String(buffer, 0, hasRead));
		}
	}

}
