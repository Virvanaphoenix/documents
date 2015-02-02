package com.sweetmanor.designpattern.strategy.cash;

public class NormalCash extends Cash {

	@Override
	public double acceptCash(double money) {
		return money;
	}

}
