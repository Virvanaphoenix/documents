package com.sweetmanor.example.thread.account;

public class DrawThread extends Thread {
	private Account account; // 账户
	private double drawAmount; // 取款数额

	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	// 没有对共享数据加锁，将涉及线程安全问题
	public void run() {
		// 账户余额大于取款数额
		if (account.getBalance() >= drawAmount) {
			// 吐出钞票
			System.out.println(getName() + "取款成功！吐出钞票：" + drawAmount);
			// 暂停当前线程，强制切换到另一个线程，以使结果明显
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			// 修改余额
			account.setBalance(account.getBalance() - drawAmount);
			System.out.println("\t余额为：" + account.getBalance());

		} else {
			System.out.println(getName() + "取款失败！余额不足。");
		}

	}

}
