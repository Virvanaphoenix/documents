package com.sweetmanor.example.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sweetmanor.common.CommonParam;

/**
 * 用输入输出流实现文件的复制
 */
public class FileOutputStreamDemo1 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(CommonParam.resourcePath + "oracle.ini"); // 创建字节输入流
			fos = new FileOutputStream(CommonParam.tempPath + "oracle_bak.ini");// 创建字节输出流
			byte[] bbuf = new byte[32];// 字节缓存数组
			int hasRead = 0;// 实际读取的字节数
			while ((hasRead = fis.read(bbuf)) > 0) {
				fos.write(bbuf, 0, hasRead);// 每次读取即写入，读多少写多少
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
			// 关闭输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
