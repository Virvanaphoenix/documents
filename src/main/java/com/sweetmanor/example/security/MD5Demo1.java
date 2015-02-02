package com.sweetmanor.example.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Demo1 {

	public byte[] encrypt(String msg) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5"); // 创建MD5信息摘要对象
			md5.update(msg.getBytes());// 更新摘要信息
			byte[] resultBytes = md5.digest();// 计算HASH结果
			return resultBytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		MD5Demo1 demo = new MD5Demo1();

		String msg = "MD5单向信息摘要";
		System.out.println("明文是：" + msg);

		byte[] enc = demo.encrypt(msg);
		System.out.println("密文字节数组：" + Arrays.toString(enc));
		System.out.println("密文是：" + new String(enc));
	}

}
