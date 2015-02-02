package com.sweetmanor.designpattern.strategy.cash;

public class CashContext {
	private Cash cash;

	public CashContext() {
	}

	public CashContext(Cash cash) {
		this.cash = cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public double getResult(double money) {
		return cash.acceptCash(money);
	}

}
