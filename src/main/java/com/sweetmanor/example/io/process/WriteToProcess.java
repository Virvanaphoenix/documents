package com.sweetmanor.example.io.process;

import java.io.IOException;
import java.io.PrintStream;

public class WriteToProcess {

	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime().exec("java ReadStandard");// 在没有包的情况下，在控制台测试成功，在程序内调用还不清楚参数
		PrintStream ps = new PrintStream(p.getOutputStream());// 以p进程的输出流创建PrintStream对象，对本程序是输出流，对p进程是输入流
		ps.println("普通字符串");
		ps.println(new WriteToProcess());
		ps.close();// 关闭前将调用flush方法强制输出
	}

}
