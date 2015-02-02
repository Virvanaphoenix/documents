package com.sweetmanor.example.io;

import java.io.FileReader;
import java.io.IOException;

import com.sweetmanor.common.CommonParam;

public class FileReaderDemo1 {

	public static void main(String[] args) {
		FileReader fr = null;
		try {
			fr = new FileReader(CommonParam.resourcePath + "oracle.ini"); // 创建字符输入流
			char[] cbuf = new char[64]; // 创建字符缓存数组
			int hasRead = 0;// 实际读取的字符数
			// 循环读取
			while ((hasRead = fr.read(cbuf)) > 0) {
				System.out.print(new String(cbuf, 0, hasRead));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
