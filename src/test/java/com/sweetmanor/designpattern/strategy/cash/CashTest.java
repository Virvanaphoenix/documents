package com.sweetmanor.designpattern.strategy.cash;

import static org.junit.Assert.*;

import org.junit.Test;

public class CashTest {

	@Test
	public void test() {
		double money = 300;
		CashContext context = new CashContext(); // 封装计算方式

		// 不打折计算方式
		Cash normalCash = new NormalCash();
		context.setCash(normalCash);
		System.out.println(context.getResult(money));

		// 打8折的计算方式
		Cash rebateCash = new RebateCash(0.8);
		context.setCash(rebateCash);
		System.out.println(context.getResult(money));

		// 返现的计算方式
		Cash returnCash = new ReturnCash(100, 10);
		context.setCash(returnCash);
		System.out.println(context.getResult(money));
	}

}
