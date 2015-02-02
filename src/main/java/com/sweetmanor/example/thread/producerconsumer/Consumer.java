package com.sweetmanor.example.thread.producerconsumer;

public class Consumer extends Thread {
	private Buffer buffer;
	private int number;

	public Consumer(Buffer buffer, int number) {

		this.buffer = buffer;
		this.number = number;
	}

	public void run() {
		while (true) {
			int v = buffer.get();
			System.out.println("消费者" + number + "消费" + v);
		}
	}
}
