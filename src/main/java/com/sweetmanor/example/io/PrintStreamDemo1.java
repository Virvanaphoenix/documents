package com.sweetmanor.example.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sweetmanor.common.CommonParam;

public class PrintStreamDemo1 {

	public static void main(String[] args) {
		PrintStream ps = null;// System.out就是一个PrintStream对象
		try {
			FileOutputStream fos = new FileOutputStream(CommonParam.tempPath
					+ "test.txt");// 创建一个节点输出流
			ps = new PrintStream(fos);// 用PrintStream包装节点输出流
			ps.println("普通字符串");
			ps.println(new PrintStreamDemo1());// 输出对象
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();// 关闭处理流，系统会自动关闭该处理流包装的节点流
			}
		}
	}

}
