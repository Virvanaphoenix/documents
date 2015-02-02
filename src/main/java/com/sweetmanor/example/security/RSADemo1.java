package com.sweetmanor.example.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class RSADemo1 {

	/**
	 * RSA加密方法
	 * 
	 * @param publicKey
	 *            公钥
	 * @param srcBytes
	 *            明文
	 * @return 密文
	 */
	public byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密对象
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);// 用公钥初始化为加密模式
				byte[] resultBytes = cipher.doFinal(srcBytes); // 进行加密操作
				return resultBytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * RSA解密方法
	 * 
	 * @param privateKey
	 *            私钥
	 * @param encBytes
	 *            密文
	 * @return 明文
	 */
	public byte[] decrypt(RSAPrivateKey privateKey, byte[] encBytes) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密对象
				cipher.init(Cipher.DECRYPT_MODE, privateKey);// 用私钥初始化为解密模式
				byte[] decBytes = cipher.doFinal(encBytes);// 进行解密操作
				return decBytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		RSADemo1 demo = new RSADemo1();

		String msg = "RSA非对称加密";
		System.out.println("明文是：" + msg);

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");// 基于RSA创建非对称加密密钥生成器
		keyPairGen.initialize(1024);// 密钥大小初始化为1024位
		KeyPair keyPair = keyPairGen.generateKeyPair();// 创建非对称密钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();// 获取私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();// 获取公钥

		byte[] encBytes = demo.encrypt(publicKey, msg.getBytes());// 调用方法进行加密操作
		System.out.println("用公钥加密后密文是：" + new String(encBytes));

		byte[] decBytes = demo.decrypt(privateKey, encBytes);// 调用方法进行解密操作
		System.out.println("用私钥解密后结果是：" + new String(decBytes));
	}

}
