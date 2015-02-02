package com.sweetmanor.example.io.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.common.Person;

/**
 * 反序列化：反序列化无需调用构造器即可生成对象。 如果一个文件中写入了多个对象，反序列化时必须按写入顺序读取
 */
public class ObjectInputStreamDemo1 {

	public static void main(String[] args) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				CommonParam.tempPath + "person.txt"));// 定义一个对象输入处理流
		Person p1 = (Person) ois.readObject();// 读取Person对象
		Person p2 = (Person) ois.readObject();// 再次读取Person对象，其实第二个Person对象的属性已改变，但序列化时会忽略
		System.out.println(p1);// 没有序列化的字段将赋予Java的默认值，此处的性别是String类型，值为null
		System.out.println(p2);
		System.out.println(p1 == p2);// 验证多次序列化的同一个对象
	}

}
