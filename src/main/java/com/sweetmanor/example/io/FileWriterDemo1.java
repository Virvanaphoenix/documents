package com.sweetmanor.example.io;

import java.io.FileWriter;
import java.io.IOException;

import com.sweetmanor.common.CommonParam;

public class FileWriterDemo1 {

	public static void main(String[] args) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(CommonParam.resourcePath + "Unicode.txt");// 创建字符输出流
			// 循环输出所有Unicode字符集
			for (int i = 0; i < 65536; i++) {
				char c = (char) i;
				fw.write(i + ":\t\t" + c + "\t\t" + Integer.toHexString(i)
						+ "\r\n"); // Window系统下换行使用\r\n，Linux系统下直接使用\n即可
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭输出流
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
