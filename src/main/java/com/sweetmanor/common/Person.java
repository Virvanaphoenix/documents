package com.sweetmanor.common;

import java.io.Serializable;

/**
 * 自定义可序列化Person类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -1790086313289511639L;
	private String name; // 名称
	private int age; // 年龄
	private transient String sex;// 性别，transient关键字指定在序列化时忽略此字段

	// 无参构造器
	public Person() {
	}

	// 包含两个字段的构造器
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// 包含三个字段的构造器
	public Person(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

	/**
	 * Java语言规范对equals方法提出的要求： 1、自反性：对于非空引用，x.equals(x)返回true
	 * 2、对称性：x.equals(y)==y.equals(x)
	 * 3、传递性：x.equals(y)返回true，y.equals(z)返回true，则x.equals(z)返回true
	 * 4、一致性：如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回相同结果
	 * 5、对于非空引用x,x.equals(null)应该返回false
	 * 
	 * 如果子类中重写equals方法，应该优先调用父类的equals方法：super.equals(other)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)// 检测是否引用同一个对象，这是经常采用的优化形式
			return true;
		if (obj == null)// null值返回false，必须的检测
			return false;
		// 比较是否是同一个类。如果子类拥有统一语义，则使用instinceof检测，这是父类应该使用final修饰符修饰equals方法
		if (getClass() != obj.getClass())
			return false;
		// if (!(obj instanceof Person)) return false;

		// 类型转换后逐个比较关注的属性值，基本类型使用==，引用类型调用equals方法
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * hashCode方法定义应该与equals方法对应，即x.equals(y)返回true，则x.hashCode()==y.hashCode()
	 * Java7的Objects工具类为hashCode方法提供了2项改进： 1、Objects.hashCode()：替代了null值的判断
	 * 2、Objects.hash()：联合多个域的hashCode值。 以下方法体可简化为：return Object.hash(age,
	 * name);
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}
