package com.sweetmanor.designpattern.decorator;

//低咖啡因
public class Decaf extends Beverage {

	public Decaf() {
		description = "Decaf";
	}

	@Override
	public double cost() {
		return 1.05;
	}

}
