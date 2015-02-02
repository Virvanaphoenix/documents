package com.sweetmanor.exam.baidustar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spacing {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tDate = sc.nextInt();
		List<Integer> out = new ArrayList<Integer>();
		for (int i = 0; i < tDate; i++) {
			int width = sc.nextInt();
			int nwords = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			int spaceLength = getSpaceLength(width, nwords, x, y, z);
			out.add(spaceLength);
		}
		for (Integer o : out) {
			System.out.println(o);
		}
	}

	private static int getSpaceLength(int width, int nwords, int x, int y, int z) {

		int spaceLength = 0;
		int[] wLength = getWordsLength(width, nwords, x, y, z);
		spaceLength = spaceLength(wLength, width);
		return spaceLength;
	}

	private static int[] getWordsLength(int width, int nwords, int x, int y,
			int z) {

		int[] wLength = new int[nwords];
		int p = (int) Math.floor((width - 1) / 2);
		wLength[0] = (x % p) + 1;
		for (int i = 1; i < nwords; i++) {
			wLength[i] = (wLength[i - 1] * y + z) % p + 1;
		}
		return wLength;
	}

	private static int spaceLength(int[] wLength, int width) {
		int spaceLength = 0;
		int length = 0;
		for (int i = 0; i < wLength.length; i++) {
			int temp = length - 1;
			length = length + wLength[i];
			if (length > width) {
				if (width - temp + 1 > spaceLength) {
					spaceLength = width - temp + 1;
				}
				length = wLength[i];
			}
			length = length + 1;
		}
		return spaceLength;
	}

}
