package com.sweetmanor.algorithm.sort;

/**
 * 选择排序示例
 * 
 * @version 1.0 2014-11-30
 * @author ijlhjj
 */
public class SelectionSort {

	/**
	 * 选择排序方法
	 */
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++)
				if (array[j] < array[index])
					index = j;
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
	}

}
