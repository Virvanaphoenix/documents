package com.sweetmanor.designpattern.strategy.duck;

//红头鸭
public class RedheadDuck extends Duck {

	public RedheadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("I'm a real Redhead duck.");
	}

}
