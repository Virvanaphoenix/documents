package com.sweetmanor.example.thread.account;

public class DrawThreadSyncCodeBlock extends Thread {
	private Account account; // 账户
	private double drawAmount; // 取款数额

	public DrawThreadSyncCodeBlock(String name, Account account,
			double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	public void run() {
		// 使用account作为同步监视器，对代码块进行加锁
		synchronized (account) {
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
}
