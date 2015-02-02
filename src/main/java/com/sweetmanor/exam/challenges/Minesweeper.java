package com.sweetmanor.exam.challenges;

import java.util.Scanner;

public class Minesweeper {

	public static void main(String[] args) {
		int n = 0, m = 0;
		int index = 0;
		char[][] in = new char[100][100];
		Scanner sc = new Scanner(System.in);
		while (true) {
			n = sc.nextInt();
			m = sc.nextInt();
			if (n == 0 & m == 0) {
				break;
			}
			index++;
			for (int i = 0; i <= n; i++) {

				String line = sc.nextLine();
				if (i == 0)
					continue;
				in[i - 1] = line.toCharArray();
			}
			int[][] out = mineNum(n, m, in);
			System.out.println("Field #" + index + ":");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (out[i][j] == 42) {
						System.out.print('*');
					} else {
						System.out.print(out[i][j]);
					}
				}
				System.out.println();
			}
			System.out.println();
		}

	}

	public static int[][] mineNum(int n, int m, char[][] in) {
		int[][] out = new int[100][100];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (in[i][j] == '*') {
					out[i][j] = '*';
				} else {
					int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
					int num = 0;
					if (j - 1 >= 0) {
						x1 = j - 1;
					} else {
						x1 = j;
					}
					if (j + 1 < m) {
						x2 = j + 1;
					} else {
						x2 = j;
					}
					if (i - 1 >= 0) {
						y1 = i - 1;
					} else {
						y1 = i;
					}
					if (i + 1 < n) {
						y2 = i + 1;
					} else {
						y2 = i;
					}
					for (int line = y1; line <= y2; line++) {
						for (int column = x1; column <= x2; column++) {
							if (in[line][column] == '*') {
								num++;
							}
						}
					}
					out[i][j] = num;

				}
			}
		}

		return out;
	}

}
