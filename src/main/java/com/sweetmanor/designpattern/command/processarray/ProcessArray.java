package com.sweetmanor.designpattern.command.processarray;

public class ProcessArray {

	public void process(int[] array, Command cmd) {
		cmd.process(array);
	}

}
