package com.sweetmanor.algorithm;

/**
 * 递归代替多层for循环示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class RecursionReplaceFor {
	private final char[] numCodes = { '0', '1', '2' };// 元素字典表
	private int num = 0; // 序号，为调试方便

	// 字典表的n位组合，在创建破解字典时非常有用
	public void replaceNFor(int n, StringBuffer prefix) {
		if (n == 1) {
			for (int i = 0; i < numCodes.length; i++)
				System.out.println((++num) + ":\t" + prefix + numCodes[i]);
		} else {
			for (int i = 0; i < numCodes.length; i++) {
				StringBuffer temp = new StringBuffer(prefix);
				temp.append(numCodes[i]);
				replaceNFor(n - 1, temp);
			}
		}
	}

	public static void main(String[] args) {
		RecursionReplaceFor rrf = new RecursionReplaceFor();
		StringBuffer prefix = new StringBuffer("");// 前缀
		rrf.replaceNFor(3, prefix);// 3位字典
	}

}
