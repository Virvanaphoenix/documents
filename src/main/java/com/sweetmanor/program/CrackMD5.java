package com.sweetmanor.program;

import java.io.FileNotFoundException;

import com.sweetmanor.util.DateUtil;
import com.sweetmanor.util.SecurityUtil;

/**
 * MD5破解示例：破解字典实时生成，效率有待改进。目前6位allCodes字典大概需要1.1小时，7位需要39.2小时
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class CrackMD5 {
	private final char[] numCodes = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' }; // 纯数字密码字典
	private final char[] charCodes = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' }; // 纯小写字母密码字典
	private final char[] allCodes = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z' }; // 包括数字和小写字母的密码字典
	private char[] passCodes = numCodes; // 实际使用破解字典

	private int num = 0; // 比对次数，每千万次归0
	private int index = 0; // 比对次数，每千万次加1
	private long start; // 每千万次的比对时间
	private long end; // 总计比对时间

	private String strCode = "";

	/**
	 * 破解MD5方法，使用了RecursionReplaceFor的递归结构
	 */
	public boolean crackMD5(int n, StringBuffer sb) {
		if (n == 1) {
			for (int i = 0; i < passCodes.length; i++) {
				String text = sb + "" + passCodes[i];
				String code = SecurityUtil.MD5Encode(text);
				if (++num > 10000000) {
					System.out.println((++index)
							+ "千万次\t本次时间："
							+ (System.currentTimeMillis() - end)
							+ "\t全部时间："
							+ DateUtil.convertMillisToString(System
									.currentTimeMillis() - start));
					end = System.currentTimeMillis();
					num = 0;
				}
				if (strCode.equalsIgnoreCase(code)) {
					System.out.println("破解成功：\t" + text + ":\t" + code);
					return true;
				}
			}
		} else {
			for (int i = 0; i < passCodes.length; i++) {
				StringBuffer temp = new StringBuffer(sb);
				temp.append(passCodes[i]);
				boolean yes = crackMD5(n - 1, temp);
				if (yes)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		CrackMD5 cmd = new CrackMD5();
		long startTime = System.currentTimeMillis();
		cmd.start = startTime;
		cmd.end = startTime;

		boolean flag = false;// 破解成功标志位
		cmd.passCodes = cmd.allCodes; // 设置破解字典，必须
		StringBuffer sb = new StringBuffer("");
		for (int i = 1; i < 7; i++) {
			flag = cmd.crackMD5(i, sb);
			if (flag)
				break;
		}
		if (!flag)
			System.out.println("在字典中未找到符合的密码！");

		long diff = System.currentTimeMillis() - startTime;
		String timeStr = DateUtil.convertMillisToString(diff);
		System.out.println("程序运行时间：\t" + timeStr);
	}

}
