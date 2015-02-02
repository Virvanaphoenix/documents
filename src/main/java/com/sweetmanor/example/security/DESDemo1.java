package com.sweetmanor.example.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESDemo1 {
	private KeyGenerator keygen; // 对称密钥生成器
	private SecretKey desKey; // 对称密钥对象
	private Cipher cipher; // 负责完成加密、解密操作
	private byte[] cipherByte; // 存放加密、解密结果

	public DESDemo1() {
		try {
			keygen = KeyGenerator.getInstance("DES");
			desKey = keygen.generateKey();
			cipher = Cipher.getInstance("DES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DES加密字符串方法
	 * 
	 * @param str
	 *            明文
	 * @return 密文
	 */
	public byte[] createEncryptor(String str) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);// 用指定密钥将cipher初始化为加密对象
			byte[] src = str.getBytes();
			cipherByte = cipher.doFinal(src);// 进行加密操作，将结果保存在cipherByte中
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherByte;
	}

	/**
	 * DES用相同密钥解密方法
	 * 
	 * @param buff
	 *            密文
	 * @return 明文
	 */
	public byte[] createDecryptor(byte[] buff) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, desKey);// 用指定密钥将cipher初始化为解密对象
			cipherByte = cipher.doFinal(buff);// 进行解密操作，将结果保存在cipherByte中
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherByte;
	}

	public static void main(String[] args) {
		DESDemo1 demo = new DESDemo1();
		String msg = "DES对称加密";
		System.out.println("明文是：" + msg);
		byte[] enc = demo.createEncryptor(msg);
		System.out.println("密文是：" + new String(enc));
		byte[] dec = demo.createDecryptor(enc);
		System.out.println("解密后的结果是：" + new String(dec));
	}

}
