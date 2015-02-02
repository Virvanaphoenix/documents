package com.sweetmanor.algorithm.sort;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import com.sweetmanor.common.RandomInt;
import com.sweetmanor.util.DateUtil;

public class SortTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	
	private static final int QUICK_SORT = 1;
	private static final int BUBBLE_SORT = 2;
	private static final int INSERETION_SORT = 3;
	private static final int QUICK_SORT_IMPROVE = 4;
	private static final int ARRAYS_SORT = 5;

	public static void testSort() {
		int[] array = RandomInt.random(); // 生成随机数组

		System.out.println("未排序的数组：");
		System.out.println(Arrays.toString(array));

		// BubbleSort.bubbleSort(array); // 冒泡排序
		// InseretionSort.insertSort(array, 0, array.length - 1);// 插入排序
		// QuickSort.quickSort(array, 0, array.length - 1); // 快速排序
		SelectionSort.selectionSort(array);// 选择排序

		System.out.println("排序后的数组：");
		System.out.println(Arrays.toString(array));
	}

	private static void testSortPerformance() {
		RandomInt.setMax(Integer.MAX_VALUE);
		int[] array = RandomInt.random(100000000); // 生成随机数组

		// sortProxy(array, "冒泡排序", BUBBLE_SORT);// 10万17秒
		// sortProxy(array, "插入排序", INSERETION_SORT);// 30万41秒
		sortProxy(array, "快速排序", QUICK_SORT);// 1亿16秒
		sortProxy(array, "改进快速排序", QUICK_SORT_IMPROVE);// 1亿13秒
		sortProxy(array, "Arrays工具类排序", ARRAYS_SORT);// 1亿12秒
	}

	public static void sortProxy(int[] array, String desc, int sortType) {
		System.out.println(desc + "：");
		int[] tempArr = Arrays.copyOf(array, array.length);// 临时数组，不对原数组排序
		// print(tempArr, false);
		sort(tempArr, sortType);
		print(tempArr, true);
		System.out.println();
	}

	public static void sort(int[] array, int sortType) {
		long beginTime = System.currentTimeMillis();
		switch (sortType) {
		case QUICK_SORT:
			QuickSort.quickSort(array, 0, array.length - 1);
			break;
		case QUICK_SORT_IMPROVE:
			QuickSortImprove.quickSort(array, 0, array.length - 1);
			break;
		case BUBBLE_SORT:
			BubbleSort.bubbleSort(array);
			break;
		case INSERETION_SORT:
			InseretionSort.insertSort(array, 0, array.length - 1);
			break;
		case ARRAYS_SORT:
			Arrays.sort(array);
			break;
		default:
		}
		long diff = System.currentTimeMillis() - beginTime;
		String timeStr = DateUtil.convertMillisToString(diff);
		System.out.println("排序耗时：" + timeStr);
	}

	private static void print(int[] array, boolean hasSort) {
		if (hasSort)
			System.out.println("排序结果（前10个数）：");
		else
			System.out.println("原始数据（前10个数）：");

		for (int i = 0; i < 10; i++)
			System.out.print(array[i] + "  ");
		System.out.println();
	}

	public static void main(String[] args) {
		testSort(); // 测试排序
		// testSortPerformance();// 测试排序性能
	}

}
