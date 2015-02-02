package com.sweetmanor.designpattern.strategy.cash;

public class RebateCash extends Cash {
	private double moneyRebate = 1; // 折扣率

	public RebateCash(double moneyRebate) {
		this.moneyRebate = moneyRebate;
	}

	@Override
	public double acceptCash(double money) {
		return money * moneyRebate;
	}

}
