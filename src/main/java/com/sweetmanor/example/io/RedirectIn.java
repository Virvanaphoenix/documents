package com.sweetmanor.example.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RedirectIn {

	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(
					"src/com/sweetmanor/example/io/RedirectIn.java");
			System.setIn(fis);// 将标准输入重定向到fis输入流
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");// 只把回车作为分隔符
			while (sc.hasNext()) {
				System.out.print(sc.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
