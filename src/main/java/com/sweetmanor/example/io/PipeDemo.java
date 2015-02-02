package com.sweetmanor.example.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeDemo {
	private static PipedInputStream pipedIS = new PipedInputStream();
	private static PipedOutputStream pipedOS = new PipedOutputStream();

	public static void main(String[] args) {
		try {
			pipedIS.connect(pipedOS);
		} catch (IOException e) {
			System.err.println("连接失败");
			System.exit(1);
		}
		byte[] inArray = new byte[10];
		int bytesRead = 0;
		startWriterThread();
		try {
			bytesRead = pipedIS.read(inArray, 0, 10);
			while (bytesRead != -1) {
				System.out.println("已经读取" + bytesRead + "字节");
				bytesRead = pipedIS.read(inArray, 0, 10);
			}
		} catch (IOException e) {
			System.err.println("读取数据错误");
			System.exit(1);
		}

	}

	private static void startWriterThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				byte[] outArray = new byte[2000];
				while (true) {
					try {
						pipedOS.write(outArray, 0, 2000);
					} catch (IOException e) {
						System.err.println("写操作错误");
						System.exit(1);
					}
					System.out.println("已经发送2000字节...");
				}
			}
		}).start();
	}
}
