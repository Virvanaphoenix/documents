package com.sweetmanor.example.collection;

import java.util.Scanner;

public class StackMemoryLeakDemo1 {
	// 测试元素个数
	private final static int count = 100;

	// 存放栈内元素的数组
	private Object[] elementDate;
	// 记录栈内元素的个数
	private int size = 0;
	// 声明栈每次增长的容量
	private int capacityIncrement;

	// 以指定容量创建栈
	public StackMemoryLeakDemo1(int initialCapacity) {
		elementDate = new Object[initialCapacity];
	}

	// 以指定容量和指定增长量创建栈
	public StackMemoryLeakDemo1(int initialCapacity, int capacityIncrement) {
		this(initialCapacity);
		this.capacityIncrement = capacityIncrement;
	}

	// 向栈顶压入一个元素
	public void push(Object object) {
		// 保证栈容量
		ensureCapacity();
		// size个元素占用了前elementDate[size - 1]的数组空间（数组下标从0开始），[size]将是下一个空元素
		elementDate[size++] = object;
	}

	/**
	 * 内存泄露分析：方法中只是返回了最后一个数组元素，并把栈长度减1，但并没有释放最后一个元素的引用。
	 * 所以对于栈来说，最后一个元素已经不在栈内了，但引用仍然有效，所以不属于垃圾回收范围，造成内存泄露。
	 * 直到有下一次入栈操作将最后一个元素的引用覆盖，泄露的内存才会成为垃圾，等待回收。
	 */
	// 从栈顶弹出一个元素
	public Object pop() {
		if (size == 0) {
			throw new RuntimeException("空栈异常");
		}
		Object ele = elementDate[--size];

		// 添加以下语句避免内存泄露
		// elementDate[size] = null;

		// 返回数组k最后一个元素后
		return ele;
	}

	// 返回栈内元素个数
	public int size() {
		return size;
	}

	/**
	 * 对象引用分析： elementDate指向一个数组内存空间，定义oldElements对象后，也指向了同一内存空间。
	 * elementDate=new Object[newLength];语句执行后，elementDate指向了新的内存空间，
	 * 退出ensureCapacity方法后，oldElements对象超出作用域。 原数组内存空间将不再有对象引用，等待垃圾回收
	 */
	// 保证底层数组能容纳栈内所有元素
	private void ensureCapacity() {
		// 增加栈的容量
		if (elementDate.length == size) {
			// 保存原有数据元素
			Object[] oldElements = elementDate;
			// 定义新的数组长度
			int newLength = 0;
			// 如果指定了增长量，增加指定容量。否则数组容量增加到原来的1.5倍
			if (capacityIncrement > 0) {
				newLength = elementDate.length + capacityIncrement;
			} else {
				newLength = (int) (elementDate.length * 1.5);
			}
			// 以新的长度创建新数组
			elementDate = new Object[newLength];
			// 将原数组元素复制到新数组中
			System.arraycopy(oldElements, 0, elementDate, 0, size);
		}
	}

	public static void main(String[] args) {
		StackMemoryLeakDemo1 stack = new StackMemoryLeakDemo1(count);
		for (int i = 0; i < count; i++) {
			stack.push("元素" + i);
		}
		for (int i = 0; i < count; i++) {
			System.out.println(stack.pop());
		}
		// 暂停程序运行，等待输入，以便观察
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
