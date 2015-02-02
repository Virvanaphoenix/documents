package com.sweetmanor.example.thread.account;

public class DrawThreadSyncCodeBlockTest {

	public static void main(String[] args) {
		Account acct = new Account("1234567", 1000);
		new DrawThreadSyncCodeBlock("丙", acct, 800).start();
		new DrawThreadSyncCodeBlock("丁", acct, 800).start();
	}
}
