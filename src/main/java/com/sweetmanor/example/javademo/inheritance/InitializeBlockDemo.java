package com.sweetmanor.example.javademo.inheritance;

/**
 * 初始化块调用示例：从输出结构中可以清晰的看到初始化块的调用流程
 * 
 * @version 1.0 2014-11-04
 * @author ijlhjj
 */
class Root {

	static {
		System.out.println("Root的静态初始化块");
	}

	{
		System.out.println("Root的普通初始化块");
	}

	public Root() {
		System.out.println("Root的无参构造器");
	}
}

class Mid extends Root {

	static {
		System.out.println("Mid的静态初始化块");
	}

	{
		System.out.println("Mid的普通初始化块");
	}

	public Mid() {
		System.out.println("Mid的无参构造器");
	}

	public Mid(String msg) {
		this();
		System.out.println("Mid的一个参数构造器，参数值：" + msg);
	}
}

class Leaf extends Mid {

	static {
		System.out.println("Leaf的静态初始化块");
	}

	{
		System.out.println("Leaf的普通初始化块");
	}

	public Leaf() {
		super("疯狂Java讲义");
		System.out.println("Leaf的无参构造器");
	}
}

public class InitializeBlockDemo {

	public static void main(String[] args) {
		new Leaf();
		System.out.println();
		new Leaf();
	}

}
