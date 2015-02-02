package com.sweetmanor.example.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHADemo1 {

	public byte[] encrypt(String msg) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");// 创建SHA信息摘要对象
			sha.update(msg.getBytes());// 更新摘要信息
			byte[] resultBytes = sha.digest();// 计算HASH结果
			return resultBytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		SHADemo1 demo = new SHADemo1();

		String msg = "SHA单向信息摘要";
		System.out.println("明文是：" + msg);

		byte[] enc = demo.encrypt(msg);
		System.out.println("密文是：" + new String(enc));
	}

}
