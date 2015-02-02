package com.sweetmanor.example.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESedeDemo1 {

	public static void main(String[] args) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("DESede"); // 对称密钥生成器
		SecretKey desKey = keygen.generateKey(); // 对称密钥
		Cipher cipher = Cipher.getInstance("DESede"); // 支持3DES的加密、解密对象

		String msg = "3DES对称加密";
		System.out.println("明文是：" + msg);

		cipher.init(Cipher.ENCRYPT_MODE, desKey);// 初始化为加密模式
		byte[] enc = cipher.doFinal(msg.getBytes()); // 获得加密结果
		System.out.println("密文是：" + new String(enc));

		cipher.init(Cipher.DECRYPT_MODE, desKey);// 初始化为解密模式
		byte[] dec = cipher.doFinal(enc);// 获得解密结果
		System.out.println("解密后的结果是：" + new String(dec));
	}
}
