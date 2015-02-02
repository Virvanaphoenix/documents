package com.sweetmanor.example.thread;

public class FourThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	private int threadNum = ++threadCount;

	public FourThread() {
		System.out.println("Making " + threadNum);
	}

	public void run() {
		while (true) {
			System.out.println("Thread " + threadNum + "(" + countDown + ")");
			if (--countDown == 0)
				return;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			new FourThread().start();
		}
		System.out.println("All Thread Started!");
	}
}
