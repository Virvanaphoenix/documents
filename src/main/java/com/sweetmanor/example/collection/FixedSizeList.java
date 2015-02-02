package com.sweetmanor.example.collection;

import java.util.Arrays;
import java.util.List;

public class FixedSizeList {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List fixedList = Arrays.asList("疯狂Java讲义", "疯狂Java EE企业应用实战");
		System.out.println(fixedList.getClass());
		for (int i = 0; i < fixedList.size(); i++) {
			System.out.println(fixedList.get(i));
		}
		fixedList.add("疯狂android讲义");
		fixedList.remove("疯狂Java讲义");

	}

}
