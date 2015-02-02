package com.sweetmanor.designpattern.strategy.cash;

public class ReturnCash extends Cash {
	private double moneyCondition = 100;// 返利条件，初始化为一个较大的值
	private double moneyReturn = 0;// 返利金额

	public ReturnCash(double moneyCondition, double moneyReturn) {
		this.moneyCondition = moneyCondition;
		this.moneyReturn = moneyReturn;
	}

	@Override
	public double acceptCash(double money) {
		double result = money;
		if (money >= moneyCondition)
			result = money - ((int) (money / moneyCondition) * moneyReturn); // 返利叠加计算，取整计算返利
		return result;
	}

}
