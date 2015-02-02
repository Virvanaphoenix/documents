package com.sweetmanor.example.security;

/**
 * 
 * @author Stars
 * 
 *         异或加密方式，对字符进行位异或加密，速度快。两次异或等于原数，不使用第三个变量完成交换也是这个道理。
 */
public class XORCiphe {
	private static final int KEY = Character.MAX_VALUE;

	/**
	 * 字符数组加密方法
	 * 
	 * @param is
	 *            明文字符数组
	 * @return 密文字符数组
	 * 
	 */
	public static char[] encode(char[] text) {
		char[] array = new char[text.length];
		for (int i = 0; i < text.length; i++) {
			array[i] = (char) (text[i] ^ KEY);
		}
		return array;

	}

	/**
	 * 字符数组解密方法
	 * 
	 * @param is
	 *            密文字符数组
	 * @return 明文字符数组
	 * 
	 */
	public static char[] decode(char[] text) {
		return encode(text);// 位加密是对称的，所以对密文的加密等于解密
	}

	public static void main(String[] args) {
		char[] text = "测试位异或加密!".toCharArray();
		char[] result = encode(text);
		System.out.println(new String(result));
		System.out.println(new String(decode(result)));
	}

}
