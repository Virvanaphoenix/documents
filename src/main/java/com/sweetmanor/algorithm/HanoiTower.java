package com.sweetmanor.algorithm;

/**
 * 汉诺塔：递归的经典例程
 * 
 * 有三根相邻的柱子，标号为 A,B,C，A柱子上从下到上按金字塔状叠放着n个不同大小的圆盘，要把所有盘子一个一个移动到柱子C上，
 * 并且每次移动同一根柱子上都不能出现大盘子在小盘子上方，请问至少需要多少次移动？
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class HanoiTower {

	// 将n个盘从from柱移动到to柱，以mid柱作为中间柱
	public void move(int n, String from, String to, String mid) {
		if (n == 1) {
			System.out.println("将 #1 号盘从：" + from + " 移动到 " + to); // 如果只剩一个盘子，直接从from柱移动到to柱
		} else {
			move(n - 1, from, mid, to); // 将 n-1 个盘从from柱先移动到mid柱
			System.out.println("将 #" + n + " 号盘从：" + from + " 移动到 " + to);// 将n号盘从from柱移动到to柱
			move(n - 1, mid, to, from); // 将 n-1 个盘从mid柱移动到to柱
		}
	}

	public static void main(String[] args) {
		HanoiTower ht = new HanoiTower();
		ht.move(3, "A", "C", "B"); // 3个圆盘，从A移动到C，借助于B
	}

}
