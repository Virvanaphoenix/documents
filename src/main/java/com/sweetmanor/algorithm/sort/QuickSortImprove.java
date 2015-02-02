package com.sweetmanor.algorithm.sort;

import java.util.Random;

import com.sweetmanor.util.NumberUtil;

/**
 * 改进的快速排序示例，注释待添加
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class QuickSortImprove {
	private static final int CUTOFF = 10;

	public static void quickSort(int[] array, int left, int right) {
		if (left + CUTOFF <= right) {
			int pivot = partition(array, left, right);
			quickSort(array, left, pivot - 1);
			quickSort(array, pivot + 1, right);
		} else {
			InseretionSort.insertSort(array, left, right);
		}
	}

	private static int partition(int[] array, int left, int right) {
		int index = left + new Random().nextInt(right - left + 1);
		int pivot = array[index];

		NumberUtil.swap(array, index, right);
		for (int i = index = left; i < right; i++)
			if (array[i] <= pivot)
				NumberUtil.swap(array, index++, i);
		NumberUtil.swap(array, index, right);

		return index;
	}

}
