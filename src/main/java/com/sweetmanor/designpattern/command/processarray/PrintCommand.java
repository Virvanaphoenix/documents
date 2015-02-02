package com.sweetmanor.designpattern.command.processarray;

public class PrintCommand implements Command {

	@Override
	public void process(int[] array) {
		for (int i : array)
			System.out.println("输出数组元素：" + i);
	}

}
