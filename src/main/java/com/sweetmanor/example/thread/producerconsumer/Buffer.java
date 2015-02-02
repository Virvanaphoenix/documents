package com.sweetmanor.example.thread.producerconsumer;

public class Buffer {
	private int contents;
	private boolean available = false;

	public synchronized int get() {
		while (!available) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int value = contents;
		available = false;
		System.out.println("取出" + contents);
		this.notifyAll();
		return value;
	}

	public synchronized void put(int value) {
		while (available) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		contents = value;
		available = true;
		System.out.println("放入" + contents);
		this.notifyAll();
	}
}
