package com.sweetmanor.example.javademo.immutable;

/**
 * final修饰变量示例：final类不可被继承，final方法不可被重新，但可以重载
 * 
 * @version 1.0 2014-11-20
 * @author ijlhjj
 * 
 *         1)类Field：必须在声明或静态初始化块中赋值 。2)实例Field：必须在声明、非静态初始化块或构造器中赋值。
 *         3)final成员变量必须显示初始化。
 */
public class FinalVariableDemo {
	private static final String name = "wenz";// 声明时赋值，相当于常量，编译时将执行宏替换
	private static final String sex;// 静态初始化块中赋值

	private final int age = 31;// 声明时赋值，相当于常量，编译时将执行宏替换
	private final String company;// 非静态初始化块中赋值
	private final String job;// 构造器中赋值

	static {
		sex = "男";
	}

	{
		company = "新晨科技";
		// final没有隐式初始化，未显式初始化前不可引用，此处将无法编译
		// System.out.println(job);
	}

	public FinalVariableDemo() {
		job = "程序员";
	}

	public static void main(String[] args) {
		FinalVariableDemo demo = new FinalVariableDemo();
		System.out.println(demo.name + "\t" + demo.sex);
		System.out.println(demo.age + "\t" + demo.company + "\t" + demo.job);
	}

}
