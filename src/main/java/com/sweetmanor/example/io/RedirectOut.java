package com.sweetmanor.example.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.sweetmanor.common.CommonParam;

public class RedirectOut {

	public static void main(String[] args) {
		PrintStream out = System.out;// System.out就是一个PrintStream对象
		PrintStream ps = null;
		try {
			FileOutputStream fos = new FileOutputStream(CommonParam.tempPath
					+ "test.txt");// 创建一个节点输出流
			ps = new PrintStream(fos);// 用PrintStream包装节点输出流
			System.setOut(ps);// 将标准输出重定向到ps输出流，重定向后标准输出内容将直接打印到文件中
			System.out.println("标准输出重定向");
			System.out.println(new RedirectOut());// 打印对象
			System.setOut(out);// 恢复标准输出
			System.out.println("程序运行完毕，请查看日志。");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();// 关闭处理流，系统会自动关闭该处理流包装的节点流
			}
		}
	}

}
