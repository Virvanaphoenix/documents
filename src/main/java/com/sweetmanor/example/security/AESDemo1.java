package com.sweetmanor.example.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESDemo1 {

	public static void main(String[] args) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");// AES对称密钥生成器
		SecretKey aesKey = keygen.generateKey();// 生成对称密钥
		Cipher cipher = Cipher.getInstance("AES");// 创建加密、解密工具对象

		String msg = "AES对称加密";
		System.out.println("明文是：" + msg);

		cipher.init(Cipher.ENCRYPT_MODE, aesKey);// 加密模式
		byte[] enc = cipher.doFinal(msg.getBytes());// 进行加密操作
		System.out.println("密文是：" + new String(enc));

		cipher.init(Cipher.DECRYPT_MODE, aesKey);// 解密模式
		byte[] dec = cipher.doFinal(enc);// 进行解密操作
		System.out.println("解密后的结果是：" + new String(dec));
	}

}
