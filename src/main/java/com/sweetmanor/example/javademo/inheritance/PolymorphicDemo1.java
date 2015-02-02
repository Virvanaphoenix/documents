package com.sweetmanor.example.javademo.inheritance;

/**
 * 多态示例
 * 
 * @version 1.0 2014-11-04
 * @author ijlhjj
 */
class BaseClass {
	public int book = 6;

	public void base() {
		System.out.println("父类的普通方法");
	}

	public void test() {
		System.out.println("父类的被覆盖的方法");
	}
}

class SubClass extends BaseClass {
	public String book = "疯狂Java讲义";

	public void sub() {
		System.out.println("子类的普通方法");
	}

	public void test() {
		System.out.println("子类的覆盖父类的方法");
	}
}

public class PolymorphicDemo1 {

	public static void main(String[] args) {
		BaseClass bc = new BaseClass();// 编译和运行时类型一致，不存在多态
		System.out.println(bc.book);
		bc.base();
		bc.test();
		System.out.println();

		SubClass sc = new SubClass();// 编译和运行时类型一致，不存在多态
		System.out.println(sc.book);
		sc.base();// 调用继承的base方法
		sc.test();// 调用子类的test方法
		System.out.println();

		BaseClass polyBc = new SubClass();// 多态示例：编译时为父类，运行时为子类
		System.out.println(polyBc.book);// Field不存在多态，此处调用编译时类型值，即父类的值
		polyBc.base();
		polyBc.test();// 方法存在多态，此处调用运行时的类型，即子类的方法

		// polyBc.sub();// BaseClass类没有提供sub方法，此处编译错误。如需调用需强制类型转换
	}

}
