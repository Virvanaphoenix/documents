package com.sweetmanor.example.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Stars
 * 
 *         凯撒密码
 */
public class CaesarCiphe {

	/**
	 * 字节数组加密方法
	 * 
	 * @param input
	 *            明文
	 * @return 密文
	 */
	public static byte[] encode(byte[] input) {
		byte[] output = new byte[input.length];// 创建密文数组
		for (int i = 0; i < input.length; i++) {
			output[i] = (byte) (input[i] + 3); // 对每一字节进行加3处理
		}
		return output;
	}

	/**
	 * 流加密方法
	 * 
	 * @param is
	 *            明文输入流
	 * @return 密文输出流
	 * @throws IOException
	 */
	public static void encode(String sourcePath) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(sourcePath); // 创建明文输入流
			fos = new FileOutputStream(sourcePath + ".caesar"); // 创建密文输出流
			byte[] bbuf = new byte[1024]; // 创建读入缓存字节数组
			int length = 0; // 读取的字节长度
			// 循环读入
			while ((length = fis.read(bbuf)) > 0) {
				byte[] output = new byte[length];// 创建输出缓存字节数组
				for (int i = 0; i < length; i++) {
					output[i] = (byte) (bbuf[i] + 3); // 对每一字节进行加3处理
				}
				fos.write(output);// 将密文写入输出流
			}
			fos.flush();// 强制输出缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 流加密方法
	 * 
	 * @param is
	 *            明文输入流
	 * @return 密文输出流
	 * @throws IOException
	 */
	public static void encode(File source, File target) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source); // 创建明文输入流
			fos = new FileOutputStream(target); // 创建密文输出流
			byte[] bbuf = new byte[1024]; // 创建读入缓存字节数组
			int length = 0; // 读取的字节长度
			// 循环读入
			while ((length = fis.read(bbuf)) > 0) {
				byte[] output = new byte[length];// 创建输出缓存字节数组
				for (int i = 0; i < length; i++) {
					output[i] = (byte) (bbuf[i] + 3); // 对每一字节进行加3处理
				}
				fos.write(output);// 将密文写入输出流
			}
			fos.flush();// 强制输出缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 字节数组解密方法
	 * 
	 * @param input
	 *            密文
	 * @return 明文
	 */
	public static byte[] decode(byte[] input) {
		byte[] output = new byte[input.length];// 创建明文数组
		for (int i = 0; i < input.length; i++) {
			output[i] = (byte) (input[i] - 3);// 对每一字节进行减3处理
		}
		return output;
	}

	/**
	 * 流解密方法
	 * 
	 * @param is
	 *            密文输入流
	 * @return 明文输出流
	 * @throws IOException
	 */
	public static void decode(String sourcePath) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(sourcePath); // 创建密文输入流
			fos = new FileOutputStream(sourcePath + ".d"); // 创建明文输出流
			byte[] bbuf = new byte[1024]; // 创建读入缓存字节数组
			int length = 0; // 读取的字节长度
			// 循环读入
			while ((length = fis.read(bbuf)) > 0) {
				byte[] output = new byte[length];// 创建输出缓存字节数组
				for (int i = 0; i < length; i++) {
					output[i] = (byte) (bbuf[i] - 3); // 对每一字节进行减3处理
				}
				fos.write(output);// 将明文写入输出流
			}
			fos.flush();// 强制输出缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 流解密方法
	 * 
	 * @param is
	 *            密文输入流
	 * @return 明文输出流
	 * @throws IOException
	 */
	public static void decode(File source, File target) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source); // 创建密文输入流
			fos = new FileOutputStream(target); // 创建明文输出流
			byte[] bbuf = new byte[1024]; // 创建读入缓存字节数组
			int length = 0; // 读取的字节长度
			// 循环读入
			while ((length = fis.read(bbuf)) > 0) {
				byte[] output = new byte[length];// 创建输出缓存字节数组
				for (int i = 0; i < length; i++) {
					output[i] = (byte) (bbuf[i] - 3); // 对每一字节进行减3处理
				}
				fos.write(output);// 将明文写入输出流
			}
			fos.flush();// 强制输出缓存
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// decode("");
		System.out.println("完成");
	}

}
