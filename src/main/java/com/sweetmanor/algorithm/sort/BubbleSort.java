package com.sweetmanor.algorithm.sort;

/**
 * 冒泡排序示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BubbleSort {

	/**
	 * 冒泡排序方法
	 */
	public static void bubbleSort(int[] array) {
		int last = array.length - 1;
		// 外层控制循环次数，内层控制比较的元素个数。采用浮起策略，每次把最小的数浮起
		for (int i = 0; i < last; i++)
			for (int j = last; j > i; j--)
				// 如果后面的元素小，则交换位置
				if (array[j] < array[j - 1]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
	}

}
