package com.sweetmanor.designpattern.simplefactory;

//老鹰类
public class Eagle implements Bird {

	@Override
	public void fly() {
		System.out.println("我是老鹰，我飞的很高");
	}
	
}
