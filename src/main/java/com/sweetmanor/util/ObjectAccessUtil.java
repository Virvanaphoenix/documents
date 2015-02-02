package com.sweetmanor.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象序列化工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ObjectAccessUtil {

	/**
	 * 写入序列化对象到文件中
	 * 
	 * @param object
	 *            可序列化对象
	 * @param filePath
	 *            文件路径
	 */
	public static void writeToFile(Serializable object, String filePath) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));// 定义一个对象输出处理流
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从文件读取对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 序列化存储的对象
	 */
	public static Object readFromFile(String filePath) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));// 定义一个对象输入处理流
			Object obj = ois.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
