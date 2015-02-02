package com.sweetmanor.algorithm.search;

import java.util.Arrays;

import com.sweetmanor.common.RandomInt;

/**
 * 二分查找示例。Arrays工具类中已有实现
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BinarySearch {

	/**
	 * 二分查找：数组必须已有序；循环实现
	 * 
	 * @param key
	 *            查找的key值
	 * @param a
	 *            待查找数组（查找范围）
	 * @return 元素在数组中的索引值，不存在返回-1
	 */
	public static int rank(int key, int[] a) {
		// return rank(key, a, 0, a.length);// 此处原先调用递归实现
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key < a[mid])
				high = mid - 1;
			else if (key > a[mid])
				low = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	/**
	 * 二分查找：数组必须已有序；递归实现
	 * 
	 * @param key
	 *            查找的key值
	 * @param a
	 *            待查找数组
	 * @param low
	 *            查找索引范围下限
	 * @param high
	 *            查找索引范围上限
	 * @return 元素在数组指定范围内的索引值，不存在返回-1
	 */
	public static int rank(int key, int[] a, int low, int high) {
		// 如果key存在于a[]中，则 low<=index<=high
		if (low > high)
			return -1;

		int mid = low + (high - low) / 2;
		if (key < a[mid])
			return rank(key, a, low, mid - 1);
		else if (key > a[mid])
			return rank(key, a, mid + 1, high);
		else
			return mid;
	}

	public static void main(String[] args) {
		int[] array = RandomInt.random(); // 生成随机数组

		Arrays.sort(array); // 二分查找必须先排序
		System.out.println(Arrays.toString(array));

		int index = rank(59, array);// 在数组中查找59
		System.out.println(index);
	}

}
