package com.sweetmanor.designpattern.simplefactory;

//鸵鸟类
public class Ostrich implements Bird {

	@Override
	public void fly() {
		System.out.println("我是鸵鸟，我只能在地上奔跑");
	}
	
}
