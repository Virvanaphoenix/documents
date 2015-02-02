package com.sweetmanor.designpattern.simplefactory;

public class BirdFactory {

	public static Bird createBird(char name) {
		// 根据传入参数确定创建那个类的对象
		switch (name) {
		case 'e':
			return new Eagle();
		case 'o':
			return new Ostrich();
		case 's':
			return new Sparrow();
		default:
			return null;
		}
	}

}
