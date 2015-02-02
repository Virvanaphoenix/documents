package com.sweetmanor.designpattern.strategy.duck;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuckTest {

	@Test
	public void test() {
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();

		Duck decoy = new DecoyDuck();
		decoy.performQuack();
		decoy.performFly();
	}

}
