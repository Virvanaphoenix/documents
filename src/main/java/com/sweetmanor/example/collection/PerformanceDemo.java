package com.sweetmanor.example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PerformanceDemo {

	public static void main(String[] args) {
		String[] tst1 = new String[900000];
		for (int i = 0; i < 900000; i++) {
			tst1[i] = String.valueOf(i);
		}
		ArrayList<Object> a1 = new ArrayList<Object>();
		for (int i = 0; i < 900000; i++) {
			a1.add(tst1[i]);
		}
		LinkedList<Object> l1 = new LinkedList<Object>();
		for (int i = 0; i < 900000; i++) {
			l1.add(tst1[i]);
		}
		long start = System.currentTimeMillis();
		for (Iterator<Object> it = a1.listIterator(); it.hasNext();) {
			it.next();

		}
		System.out.println(System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		for (Iterator<Object> it = l1.iterator(); it.hasNext();) {
			it.next();
		}
		System.out.println(System.currentTimeMillis() - start);

	}

}
