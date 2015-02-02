package com.sweetmanor.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;

public class Log {
	public static Logger logger = Logger.getLogger(CommonParam.class);// LOG4J日志记录

	public static void write(List<List<String>> content, String separator) {
		write("./logs/result.txt", content, separator);
	}

	public static void write(String fileName, List<List<String>> content,
			String separator) {
		PrintStream out = System.out;
		try (FileOutputStream fos = new FileOutputStream(fileName);
				PrintStream ps = new PrintStream(fos);) {
			System.setOut(ps);// 将标准输出重定向到ps输出流，重定向后标准输出内容将直接打印到文件中

			for (int i = 0; i < content.size(); i++) {
				List<String> row = content.get(i);
				System.out.print("行" + i + "：" + separator + separator);
				for (int j = 0; j < row.size(); j++) {
					String cell = row.get(j);
					System.out.print("列" + (j + 1) + "：" + cell + separator);
				}
				System.out.println();
			}

			System.setOut(out);// 恢复标准输出
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
