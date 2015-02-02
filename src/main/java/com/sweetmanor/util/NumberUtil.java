package com.sweetmanor.util;

/**
 * 数值工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class NumberUtil {

	/**
	 * 整型数组元素交换函数
	 * 
	 * @param array
	 *            待交换元素数组
	 * @param i
	 *            元素索引
	 * @param j
	 *            元素索引
	 */
	public static void swap(int array[], int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 将二进制转化为十六进制字符串
	 */
	public static String byteToHex(byte[] b) {
		StringBuffer result = new StringBuffer(""); // 定义返回结果字符串，初始化为空
		String temp = "";
		// 循环处理每个字节
		for (int i = 0; i < b.length; i++) {
			temp = Integer.toHexString(b[i] & 0XFF);// 把当前字节转换为十六进制，按位与保证只保留最后8位，在此处应该没有影响
			// 如果转换结果长度为1，进行前面添加0的处理，否则直接加入结果字符串
			if (temp.length() == 1)
				result.append("0" + temp);
			else
				result.append(temp);
		}
		return result.toString().toUpperCase();// 返回结果的大写形式
	}

	/**
	 * 十六进制转换为二进制
	 */
	public static byte[] hexToByte(String hex) {
		byte[] result = new byte[8];// 定义返回结果数组
		byte[] b = hex.getBytes();// 获取传入参数的字节数组
		for (int i = 0; i < 8; i++) {
			result[i] = uniteBytes(b[i * 2], b[i * 2 + 1]);// 将传入数组每2位拼装成一位作为结果
		}
		return result;
	}

	/**
	 * 将两个字节拼装成一个字节，网上找到，先不研究
	 */
	private static byte uniteBytes(byte b1, byte b2) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { b1 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { b2 }))
				.byteValue();
		byte result = (byte) (_b0 ^ _b1);
		return result;
	}

}
