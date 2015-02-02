package com.sweetmanor.designpattern.strategy.duck;

public class DecoyDuck extends Duck {

	public DecoyDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("I'm a Decoy duck.");
	}

}
