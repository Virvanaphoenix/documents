package com.sweetmanor.example.javademo.inheritance;

/**
 * 构造器调用示例：从输出结构中可以清晰的看到构造器的调用流程
 * 
 * @version 1.0 2014-11-04
 * @author ijlhjj
 * 
 *         this调用重载构造器和super调用父类构造器都必须出现在构造器的第一行，所以this和super不会同时出现
 */
class Creature {

	public Creature() {
		System.out.println("Creature无参数的构造器");
	}

}

class Animal extends Creature {

	public Animal(String name) {
		// 如果没有显示的构造器调用，将在创建子类之前调用父类的默认构造器，此处将调用Creature的无参构造器
		System.out.println("Animal带一个参数的构造器，该动物的name为" + name);
	}

	public Animal(String name, int age) {
		// 此处使用this显示调用重载构造器
		this(name);
		System.out.println("Animal带两个参数的构造器，其age为" + age);
	}

}

public class Wolf extends Animal {

	public Wolf() {
		// 此处使用super显示调用父类构造器
		super("灰太狼", 3);
		System.out.println("Wolf无参数的构造器");
	}

	public static void main(String[] args) {
		new Wolf();
	}

}
