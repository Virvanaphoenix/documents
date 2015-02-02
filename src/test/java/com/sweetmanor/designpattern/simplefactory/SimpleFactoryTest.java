package com.sweetmanor.designpattern.simplefactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleFactoryTest {

	@Test
	public void test() {
		Bird bird = BirdFactory.createBird('o');
		bird.fly();
		bird = BirdFactory.createBird('e');
		bird.fly();
		bird = BirdFactory.createBird('s');
		bird.fly();
	}

}
