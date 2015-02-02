package com.sweetmanor.exam.common;

/**
 * 小球反弹：一小球从100米高度落下，每次落地后反弹回原高度的一半。求它在第10次落地时，共经过多少米？第10次反弹多高？
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ReboundHeight {

	public static void main(String[] args) {
		double height = 100;
		double distance = 0;

		for (int i = 0; i < 10; i++) {
			distance += 2 * height;
			height = height / 2;
		}
		distance -= 100;

		System.out.println("总共经过路程：\t" + distance);
		System.out.println("第10次反弹高度：\t" + height);
	}

}
