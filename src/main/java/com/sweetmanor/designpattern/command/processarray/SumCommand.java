package com.sweetmanor.designpattern.command.processarray;

public class SumCommand implements Command {

	@Override
	public void process(int[] array) {
		int sum = 0;
		for (int i : array)
			sum += i;
		System.out.println("数组元素的总和是：" + sum);
	}

}
