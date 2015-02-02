package com.sweetmanor.designpattern.strategy.duck;

//橡皮鸭
public class RubberDuck extends Duck {

	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}

	@Override
	public void display() {
		System.out.println("I'm a Rubber duck.");
	}

}
