package com.sweetmanor.example.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromProcessDemo1 {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec("javac");// 获取运行javac命令的子进程
			br = new BufferedReader(new InputStreamReader(p.getErrorStream()));// 以p进程的错误流创建BufferedReader对象
			String buffer = null;
			// 循环读取
			while ((buffer = br.readLine()) != null) {
				System.out.println(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

}
