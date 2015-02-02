package com.sweetmanor.example.io.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.common.Person;

/**
 * 输出可序列化对象到本地磁盘
 */
public class ObjectOutputStreamDemo1 {

	public static void main(String[] args) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				CommonParam.tempPath + "person.txt"));// 定义一个对象输出处理流
		Person p = new Person("孙悟空", 500, "男");// 可序列化对象Person，其中性别是不序列化的字段
		oos.writeObject(p);// 把Person对象写入本地磁盘
		p.setName("猪八戒");// 改变Person对象的可变属性
		/*
		 * 再次把Person对象写入磁盘。Java序列化机制对此的处理是：只在第一次时把对象的字节数组进行序列化，
		 * 之后的序列化将只写一个指向第一次序列化的地址码，在ObjectInputStreamDemo1中可看到效果
		 */
		oos.writeObject(p);
	}

}
