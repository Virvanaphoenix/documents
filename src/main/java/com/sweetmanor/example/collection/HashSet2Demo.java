package com.sweetmanor.example.collection;

import java.util.HashSet;

class A {
	public boolean equals(Object obj) {
		return true;
	}
}

class B {
	public int hashCode() {
		return 1;
	}
}

class C {
	public boolean equals(Object obj) {
		return true;
	}

	public int hashCode() {
		return 2;
	}
}

public class HashSet2Demo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Object> books = new HashSet<Object>();
		books.add(new A());
		books.add(new A());
		books.add(new B());
		books.add(new B());
		books.add(new C());
		books.add(new C());
		System.out.println(books);
	}
}
