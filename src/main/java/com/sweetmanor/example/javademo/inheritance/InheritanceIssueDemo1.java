package com.sweetmanor.example.javademo.inheritance;

/**
 * 父类构造器调用子类重新方法报错示例：由此处可看出初始化子类时的调用流程是：先调用子类构造器，子类构造器先调用父类构造器实例化父类，然后初始化子类属性域（
 * 是设置初始值，不是分配内存空间）（ 此处的属性设置初始值和初始化块是处于一个优先级别的，谁在前谁先执行）， 最后执行子类构造器
 * 
 * @version 1.0 2014-11-04
 * @author ijlhjj
 */
class Base {

	public Base() {
		test();// 子类重写此方法后，初始化子类将调用子类的方法
	}

	public void test() {
		System.out.println("父类的test方法");
	}
}

class Sub extends Base {
	private String name = "Java";

	public void test() {
		System.out.println("子类的test方法");
		System.out.println("name长度为：" + name.length());// 此时的name属性还没有初始化，将报空指针异常
	}
}

public class InheritanceIssueDemo1 {

	public static void main(String[] args) {
		new Base();
		new Sub();
	}

}
