package com.sweetmanor.example.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 使用DSA实现数字签名
 */
public class SignatureDemo2 {

	public static void main(String[] args) throws Exception {

		String msg = "DSA数字签名";
		System.out.println("原文是：" + msg);
		byte[] msgBytes = msg.getBytes();

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();// 生成密钥对

		Signature sign = Signature.getInstance("DSA");// 指定用DSA创建数字签名对象
		PrivateKey privateKey = keyPair.getPrivate();// 获取私钥，用私钥进行签名
		sign.initSign(privateKey);// 用私钥初始化签名操作
		sign.update(msgBytes);// 更新签名内容
		byte[] signBytes = sign.sign();// 进行签名操作，得到结果数组
		System.out.println("签名是：" + new String(signBytes));

		PublicKey publicKey = keyPair.getPublic();// 获取公钥，用公钥进行验证
		sign.initVerify(publicKey);// 用公钥初始化验证操作
		sign.update(msgBytes);// 再次更新签名内容
		// 进行验证，输出验证信息
		if (sign.verify(signBytes)) {
			System.out.println("签名验证成功");
		} else {
			System.out.println("签名验证失败");
		}
	}

}
