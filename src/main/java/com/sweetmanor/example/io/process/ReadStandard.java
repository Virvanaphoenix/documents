package com.sweetmanor.example.io.process;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.sweetmanor.common.CommonParam;

public class ReadStandard {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);// 获取标准输入流
		PrintStream ps = new PrintStream(CommonParam.tempPath + "test.txt");
		sc.useDelimiter("\n");// 只把回车作为分隔符
		while (sc.hasNext()) {
			ps.println("从进程输入流中获取的内容是：\t" + sc.next());
		}
		ps.close();
	}

}
