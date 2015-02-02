package com.sweetmanor.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThirdThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int i = 0;
		for (; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
		}
		return i;
	}

	public static void main(String[] args) {
		ThirdThread tt = new ThirdThread();
		FutureTask<Integer> task = new FutureTask<Integer>(tt);
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
			if (i == 20) {
				new Thread(task, "有返回值的线程").start();
			}
		}
		try {
			System.out.println("子线程的返回值：" + task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
