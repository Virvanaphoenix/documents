package com.sweetmanor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sweetmanor.common.CommonParam;

/**
 * 安全工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SecurityUtil {
	private static final String DefaultKeyDir = "";

	/**
	 * 生成字符串MD5信息摘要
	 */
	public static String MD5Encode(String content) {
		return messageDigest(content, "MD5");
	}

	/**
	 * 生成文件MD5信息摘要
	 */
	public static String MD5Encode(File file) {
		return messageDigest(file, "MD5");
	}

	/**
	 * 生成字符串SHA信息摘要
	 */
	public static String SHAEncode(String content) {
		return messageDigest(content, "SHA");
	}

	/**
	 * 生成文件SHA信息摘要
	 */
	public static String SHAEncode(File file) {
		return messageDigest(file, "SHA");
	}

	/**
	 * 计算字符串信息摘要公共方法
	 * 
	 * @param content
	 *            字符串
	 * @param algorithm
	 *            算法
	 * @return 信息摘要
	 */
	private static String messageDigest(String content, String algorithm) {
		String result = null;// 返回结果默认为null
		try {
			byte[] bytesInput = content.getBytes();
			MessageDigest md = MessageDigest.getInstance(algorithm); // 创建信息摘要对象
			md.update(bytesInput);// 更新计算摘要内容
			byte[] bytesOuput = md.digest();// 计算Hash结果
			result = NumberUtil.byteToHex(bytesOuput);// 调用方法将字节数组转换为十六进制字符串
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算文件信息摘要公共方法
	 * 
	 * @param file
	 *            文件
	 * @param algorithm
	 *            算法
	 * @return 信息摘要
	 */
	private static String messageDigest(File file, String algorithm) {
		String result = null;// 返回结果默认为null
		FileChannel channel = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);// 创建信息摘要对象
			FileInputStream fis = new FileInputStream(file);// 创建文件输入节点流
			channel = fis.getChannel();// 获取FileChannel
			ByteBuffer bBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的ByteBuffer缓冲区
			// 循环读取文件内容
			while (channel.read(bBuffer) != -1) {
				bBuffer.flip();// 为从ByteBuffer中读数据做准备
				md.update(bBuffer);// 更新计算摘要内容
				bBuffer.clear();// 为下一次向ByteBuffer中写数据做准备
			}
			byte[] bytesOuput = md.digest();// 计算Hash结果
			result = NumberUtil.byteToHex(bytesOuput);// 调用方法将字节数组转换为十六进制字符串
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 如果FileChannel不为空，释放FileChannel对象
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * AES加密字符串
	 * 
	 * @param content
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文字节数组
	 */
	public static byte[] AESEncode(String content, SecretKey key) {
		// 如果密钥为空，使用默认密钥
		if (key == null)
			key = (SecretKey) ObjectAccessUtil.readFromFile(DefaultKeyDir
					+ "AESKey");
		return encode(content.getBytes(), "AES", key, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * AES解密字符串
	 * 
	 * @param content
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文字节数组
	 */
	public static byte[] AESDecode(byte[] content, SecretKey key) {
		// 如果密钥为空，使用默认密钥
		if (key == null)
			key = (SecretKey) ObjectAccessUtil.readFromFile(DefaultKeyDir
					+ "AESKey");
		return encode(content, "AES", key, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * AES加密文件
	 * 
	 * @param sourcePath
	 *            待加密文件路径
	 * @param targetPath
	 *            加密文件存储路径
	 * @param key
	 *            密钥
	 */
	public static void AESEncode(String sourcePath, String targetPath,
			SecretKey key) {
		// 如果密钥为空，使用默认密钥
		if (key == null)
			key = (SecretKey) ObjectAccessUtil.readFromFile(DefaultKeyDir
					+ "AESKey");
		encode(sourcePath, targetPath, "AES", key, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * AES解密文件
	 * 
	 * @param sourcePath
	 *            待解密文件路径
	 * @param targetPath
	 *            解密后文件存储路径
	 * @param key
	 *            密钥
	 */
	public static void AESDecode(String sourcePath, String targetPath,
			SecretKey key) {
		// 如果密钥为空，使用默认密钥
		if (key == null)
			key = (SecretKey) ObjectAccessUtil.readFromFile(DefaultKeyDir
					+ "AESKey");
		encode(sourcePath, targetPath, "AES", key, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * 对称 加密/解密 字符串公共算法
	 * 
	 * @param content
	 *            待 加密/解密 字节数组
	 * @param algorithm
	 *            算法
	 * @param key
	 *            密钥
	 * @param isEncode
	 *            加密、解密标识，只接受Cipher.ENCRYPT_MODE和Cipher.DECRYPT_MODE两个参数
	 * @return 加密/解密 后的字节数组
	 */
	private static byte[] encode(byte[] content, String algorithm,
			SecretKey key, int isEncode) {
		byte[] result = null;// 返回结果默认为null
		// 如果密钥为空，返回null
		if (key == null
				|| algorithm == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return null;

		try {
			Cipher cipher = Cipher.getInstance(algorithm);// 创建加密、解密工具对象
			cipher.init(isEncode, key);
			result = cipher.doFinal(content);// 进行 加密/解密 操作
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对称 加密/解密 文件
	 * 
	 * @param sourcePath
	 *            待 加密/解密 文件路径
	 * @param targetPath
	 *            加密/解密 后文件存储路径
	 * @param algorithm
	 *            算法
	 * @param key
	 *            密钥
	 * @param isEncode
	 *            加密/解密 标识
	 */
	private static void encode(String sourcePath, String targetPath,
			String algorithm, SecretKey key, int isEncode) {
		// 如果密钥为空，不进行任何操作
		if (key == null
				|| algorithm == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return;

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			Cipher cipher = Cipher.getInstance(algorithm);// 创建加密、解密工具对象
			cipher.init(isEncode, key);// 初始化
			inChannel = new FileInputStream(sourcePath).getChannel();// 以源文件输入流创建Channel
			outChannel = new FileOutputStream(targetPath).getChannel();// 以目标文件输出流创建Channel
			ByteBuffer inBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的输入缓冲区
			ByteBuffer outBuffer = ByteBuffer.allocate(1024);// 创建一个同样大小的输出缓冲区
			// 循环读取文件内容
			while (inChannel.read(inBuffer) != -1) {
				inBuffer.flip();// 从inBuffer读数据
				outBuffer.clear();// 向outBuffer写数据
				cipher.doFinal(inBuffer, outBuffer);// 执行 加密/解密 操作
				outBuffer.flip();// 从outBuffer读数据
				outChannel.write(outBuffer);// 把加密结果写入outChannel流
				inBuffer.clear();// 向inBuffer写数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放inChannel
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 释放outChannel
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * RSA公钥加密字符串
	 * 
	 * @param content
	 *            明文
	 * @param publicKey
	 *            公钥
	 * @return 密文字节数组
	 */
	public static byte[] RSAEncode(String content, RSAPublicKey publicKey) {
		// 如果密钥为空，使用默认密钥
		if (publicKey == null)
			publicKey = (RSAPublicKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "publicRSAKey");
		return encode(content.getBytes(), publicKey, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * RSA公钥解密字符串
	 * 
	 * @param content
	 *            密文字节数组
	 * @param publicKey
	 *            公钥
	 * @return 明文字节数组
	 */
	public static byte[] RSADecode(byte[] content, RSAPublicKey publicKey) {
		// 如果密钥为空，使用默认密钥
		if (publicKey == null)
			publicKey = (RSAPublicKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "publicRSAKey");
		return encode(content, publicKey, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * RSA私钥加密字符串
	 * 
	 * @param content
	 *            明文
	 * @param privateKey
	 *            私钥
	 * @return 密文字节数组
	 */
	public static byte[] RSAEncode(String content, RSAPrivateKey privateKey) {
		// 如果密钥为空，使用默认密钥
		if (privateKey == null)
			privateKey = (RSAPrivateKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "privateRSAKey");
		return encode(content.getBytes(), privateKey, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * RSA私钥解密字符串
	 * 
	 * @param content
	 *            密文字节数组
	 * @param privateKey
	 *            私钥
	 * @return 明文字节数组
	 */
	public static byte[] RSADecode(byte[] content, RSAPrivateKey privateKey) {
		// 如果密钥为空，使用默认密钥
		if (privateKey == null)
			privateKey = (RSAPrivateKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "privateRSAKey");
		return encode(content, privateKey, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * RSA公钥加密文件
	 * 
	 * @param sourcePath
	 *            待加密文件路径
	 * @param targetPath
	 *            加密后目标文件存储路径
	 * @param publicKey
	 *            公钥
	 */
	public static void RSAEncode(String sourcePath, String targetPath,
			RSAPublicKey publicKey) {
		// 如果密钥为空，使用默认密钥
		if (publicKey == null)
			publicKey = (RSAPublicKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "publicRSAKey");
		encode(sourcePath, targetPath, publicKey, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * RSA公钥解密文件
	 * 
	 * @param sourcePath
	 *            待解密文件路径
	 * @param targetPath
	 *            解密后目标文件存储路径
	 * @param publicKey
	 *            公钥
	 */
	public static void RSADecode(String sourcePath, String targetPath,
			RSAPublicKey publicKey) {
		// 如果密钥为空，使用默认密钥
		if (publicKey == null)
			publicKey = (RSAPublicKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "publicRSAKey");
		encode(sourcePath, targetPath, publicKey, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * RSA私钥加密文件
	 * 
	 * @param sourcePath
	 *            待加密文件路径
	 * @param targetPath
	 *            加密后目标文件存储路径
	 * @param privateKey
	 *            私钥
	 */
	public static void RSAEncode(String sourcePath, String targetPath,
			RSAPrivateKey privateKey) {
		// 如果密钥为空，使用默认密钥
		if (privateKey == null)
			privateKey = (RSAPrivateKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "privateRSAKey");
		encode(sourcePath, targetPath, privateKey, Cipher.ENCRYPT_MODE);// 调用方法进行加密操作
	}

	/**
	 * RSA私钥解密文件
	 * 
	 * @param sourcePath
	 *            待解密文件路径
	 * @param targetPath
	 *            解密后目标文件存储路径
	 * @param privateKey
	 *            私钥
	 */
	public static void RSADecode(String sourcePath, String targetPath,
			RSAPrivateKey privateKey) {
		// 如果密钥为空，使用默认密钥
		if (privateKey == null)
			privateKey = (RSAPrivateKey) ObjectAccessUtil
					.readFromFile(DefaultKeyDir + "privateRSAKey");
		encode(sourcePath, targetPath, privateKey, Cipher.DECRYPT_MODE);// 调用方法进行解密操作
	}

	/**
	 * RSA算法公钥 加密/解密 字符串公共方法
	 * 
	 * @param content
	 *            待 加密/解密 字符串
	 * @param publicKey
	 *            公钥
	 * @param isEncode
	 *            加密/解密 操作标识符
	 * @return 加密/解密 后字节数组
	 */
	private static byte[] encode(byte[] content, RSAPublicKey publicKey,
			int isEncode) {
		byte[] result = null; // 默认返回结果为null
		// 如果密钥为空，返回null
		if (publicKey == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return null;

		try {
			Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密对象
			cipher.init(isEncode, publicKey);// 初始化
			result = cipher.doFinal(content); // 进行 加密/解密 操作
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * RSA算法私钥 加密/解密 字符串公共方法
	 * 
	 * @param content
	 *            待 加密/解密 字符串
	 * @param privateKey
	 *            私钥
	 * @param isEncode
	 *            加密/解密 操作标识符
	 * @return 加密/解密 后字节数组
	 */
	private static byte[] encode(byte[] content, RSAPrivateKey privateKey,
			int isEncode) {
		byte[] result = null; // 默认返回结果为null
		// 如果密钥为空，返回null
		if (privateKey == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return null;

		try {
			Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密对象
			cipher.init(isEncode, privateKey);// 初始化
			result = cipher.doFinal(content); // 进行 加密/解密 操作
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * RSA算法公钥 加密/解密 文件公共方法
	 * 
	 * @param sourcePath
	 *            待 加密/解密 文件路径
	 * @param targetPath
	 *            加密/解密 后文件存储路径
	 * @param publicKey
	 *            公钥
	 * @param isEncode
	 *            加密/解密 标识
	 */
	private static void encode(String sourcePath, String targetPath,
			RSAPublicKey publicKey, int isEncode) {
		// 如果密钥为空，返回null
		if (publicKey == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return;

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密工具对象
			cipher.init(isEncode, publicKey);// 初始化
			inChannel = new FileInputStream(sourcePath).getChannel();// 以源文件输入流创建Channel
			outChannel = new FileOutputStream(targetPath).getChannel();// 以目标文件输出流创建Channel
			ByteBuffer inBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的输入缓冲区
			ByteBuffer outBuffer = ByteBuffer.allocate(1024);// 创建一个同样大小的输出缓冲区
			// 循环读取文件内容
			while (inChannel.read(inBuffer) != -1) {
				inBuffer.flip();// 从inBuffer读数据
				outBuffer.clear();// 向outBuffer写数据
				cipher.doFinal(inBuffer, outBuffer);// 执行加密/解密操作
				outBuffer.flip();// 从outBuffer读数据
				outChannel.write(outBuffer);// 把加密结果写入outChannel流
				inBuffer.clear();// 向inBuffer写数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放inChannel
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 释放outChannel
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * RSA算法私钥 加密/解密 文件公共方法
	 * 
	 * @param sourcePath
	 *            待加密/解密文件路径
	 * @param targetPath
	 *            加密/解密后文件存储路径
	 * @param privateKey
	 *            私钥
	 * @param isEncode
	 *            加密/解密标识
	 */
	private static void encode(String sourcePath, String targetPath,
			RSAPrivateKey privateKey, int isEncode) {
		// 如果密钥为空，返回null
		if (privateKey == null
				|| (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE))
			return;

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");// 创建RSA加密、解密工具对象
			cipher.init(isEncode, privateKey);// 初始化
			inChannel = new FileInputStream(sourcePath).getChannel();// 以源文件输入流创建Channel
			outChannel = new FileOutputStream(targetPath).getChannel();// 以目标文件输出流创建Channel
			ByteBuffer inBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的输入缓冲区
			ByteBuffer outBuffer = ByteBuffer.allocate(1024);// 创建一个同样大小的输出缓冲区
			// 循环读取文件内容
			while (inChannel.read(inBuffer) != -1) {
				inBuffer.flip();// 从inBuffer读数据
				outBuffer.clear();// 向outBuffer写数据
				cipher.doFinal(inBuffer, outBuffer);// 执行加密操作
				outBuffer.flip();// 从outBuffer读数据
				outChannel.write(outBuffer);// 把加密结果写入outChannel流
				inBuffer.clear();// 向inBuffer写数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放inChannel
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 释放outChannel
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 生成对称密钥的方法
	 * 
	 * @param algorithm
	 *            生成密钥的算法
	 * @param keyFileName
	 *            密钥保存的文件名
	 * @return 成功返回true，失败返回false
	 */
	public static boolean createSecretKey(String algorithm, String keyFileName) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(algorithm);// 创建密钥生成器
			SecretKey key = keygen.generateKey();// 生成密钥
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(CommonParam.resourcePath + keyFileName));// 定义一个对象输出处理流
			oos.writeObject(key);// 把密钥写入指定文件
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 生成非对称密钥的方法
	 * 
	 * @param algorithm
	 *            生成密钥的算法
	 * @param keyFileName
	 *            密钥保存的文件名，公钥前将添加public前缀，私钥前将添加private前缀
	 * @return 成功返回true，失败返回false
	 */
	public static boolean createKeyPair(String algorithm, String keyFileName) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator
					.getInstance(algorithm);// 创建非对称密钥生成器
			keyPairGen.initialize(1024);// 密钥大小初始化为1024位
			KeyPair keyPair = keyPairGen.generateKeyPair();// 创建非对称密钥
			PrivateKey privateKey = keyPair.getPrivate();// 获取私钥
			PublicKey publicKey = keyPair.getPublic();// 获取公钥
			ObjectOutputStream privateOOS = new ObjectOutputStream(
					new FileOutputStream(CommonParam.resourcePath + "private"
							+ keyFileName));// 定义公钥输出处理流
			privateOOS.writeObject(privateKey);// 把私钥写入指定文件
			ObjectOutputStream publicOOS = new ObjectOutputStream(
					new FileOutputStream(CommonParam.resourcePath + "public"
							+ keyFileName));// 定义公钥输出处理流
			publicOOS.writeObject(publicKey);// 把公钥写入指定文件
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		SecurityUtil.createKeyPair("RSA", "esc");
	}

}
