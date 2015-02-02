package com.sweetmanor.example.io;

import java.io.FileInputStream;
import java.io.IOException;

import com.sweetmanor.common.CommonParam;

public class FileInputStreamDemo1 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(CommonParam.resourcePath + "oracle.ini");// 创建字节输入流
			byte[] bbuf = new byte[1024]; // 创建缓存字节数组
			int hasRead = 0; // 实际读取的字节数
			// 循环读入
			while ((hasRead = fis.read(bbuf)) > 0) {
				System.out.print(new String(bbuf, 0, hasRead));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
