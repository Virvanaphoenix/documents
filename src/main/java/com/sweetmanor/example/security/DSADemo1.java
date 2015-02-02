package com.sweetmanor.example.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

import javax.crypto.Cipher;

/**
 * 报错，待修改
 */
public class DSADemo1 {

	/**
	 * DSA加密方法
	 * 
	 * @param publicKey
	 *            公钥
	 * @param srcBytes
	 *            明文
	 * @return 密文
	 */
	public byte[] encrypt(DSAPublicKey publicKey, byte[] srcBytes) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("DSA");// 创建DSA加密、解密对象
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
	 * DSA解密方法
	 * 
	 * @param privateKey
	 *            私钥
	 * @param encBytes
	 *            密文
	 * @return 明文
	 */
	public byte[] decrypt(DSAPrivateKey privateKey, byte[] encBytes) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("DSA");// 创建DSA加密、解密对象
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
		DSADemo1 demo = new DSADemo1();

		String msg = "DSA非对称加密";
		System.out.println("明文是：" + msg);

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");// 基于DSA创建非对称加密密钥生成器
		keyPairGen.initialize(1024);// 密钥大小初始化为1024位
		KeyPair keyPair = keyPairGen.generateKeyPair();// 创建非对称密钥
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();// 获取私钥
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();// 获取公钥

		byte[] encBytes = demo.encrypt(publicKey, msg.getBytes());// 调用方法进行加密操作
		System.out.println("用公钥加密后密文是：" + new String(encBytes));

		byte[] decBytes = demo.decrypt(privateKey, encBytes);// 调用方法进行解密操作
		System.out.println("用私钥解密后结果是：" + new String(decBytes));
	}

}
