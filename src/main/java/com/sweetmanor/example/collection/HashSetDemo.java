package com.sweetmanor.example.collection;

import java.util.HashSet;
import java.util.Iterator;

class R {
	int count;

	public R(int count) {
		this.count = count;
	}

	public String toString() {
		return "R[count:" + count + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj.getClass() == R.class) {
			R r = (R) obj;
			if (r.count == this.count)
				return true;
		}
		return false;
	}

	public int hashCode() {
		return this.count;
	}
}

public class HashSetDemo {
	public static void main(String[] args) {
		HashSet<R> hs = new HashSet<R>();
		hs.add(new R(5));
		hs.add(new R(-3));
		hs.add(new R(9));
		hs.add(new R(-2));
		System.out.println(hs);
		Iterator<R> it = hs.iterator();
		R first = (R) it.next();
		first.count = -3;
		System.out.println(hs);
		hs.remove(new R(-3));
		System.out.println(hs);
		System.out.println(hs.contains(new R(-3)));
		System.out.println(hs.contains(new R(5)));

	}
}
