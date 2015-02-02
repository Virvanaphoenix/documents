package com.sweetmanor.designpattern.simplefactory;

//麻雀类
public class Sparrow implements Bird {

	@Override
	public void fly() {
		System.out.println("我是麻雀，飞上枝头后叫凤凰");
	}
	
}
