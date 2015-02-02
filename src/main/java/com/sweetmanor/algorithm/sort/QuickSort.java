package com.sweetmanor.algorithm.sort;

import java.util.Random;

import com.sweetmanor.util.NumberUtil;

/**
 * 快速排序示例，注释待添加
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class QuickSort {

	/**
	 * 快速排序函数
	 * 
	 * @param array
	 *            待排序数组
	 * @param left
	 *            左边界
	 * @param right
	 *            右边界
	 */
	public static void quickSort(int[] array, int left, int right) {
		// 左边界小于右边界说明还有未排序元素
		if (left < right) {
			int pivot = partition(array, left, right); // 调用实际排序函数
			quickSort(array, left, pivot - 1); // 递归排序左半部分
			quickSort(array, pivot + 1, right); // 递归排序右半部分
		}
	}

	// 实际排序函数，为保证任何情况下效率都不会最差，采用随机选取
	private static int partition(int[] array, int left, int right) {
		int index = left + new Random().nextInt(right - left + 1); // 随机选取一个索引
		int pivot = array[index];

		NumberUtil.swap(array, index, right);
		for (int i = index = left; i < right; i++)
			if (array[i] <= pivot)
				NumberUtil.swap(array, index++, i);
		NumberUtil.swap(array, index, right);

		return index;
	}

}
