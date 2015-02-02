package com.sweetmanor.example.thread;

public class SecondThread implements Runnable {
	// 多个线程可共享一个线程目标实例，当共享一个线程目标实例时，目标实例的实例属性是共享的
	private int i;

	@Override
	public void run() {
		for (; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
			if (i == 20) {
				// 创建1个目标实例，并用它启动2个新线程
				SecondThread st = new SecondThread();
				new Thread(st, "新线程1").start();
				new Thread(st, "新线程2").start();
			}
		}
	}
}
