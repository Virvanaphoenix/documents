package com.sweetmanor.example.javademo;

/**
 * String类中各种方法的使用
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class StringDemo1 {

	public static void main(String[] args) {
		testFormat();
		testSplit();
	}

	/**
	 * format方法：格式化输出，可用于批量替换
	 */
	public static void testFormat() {
		String formatArg = "本次抽取观众人员：\n\t%1$s\n恭喜%1$s成为本次观众抽奖的大奖得主。"
				+ "\n\n我们将为%1$s颁发：\n\t过期的酸奶二十箱。";
		String info = String.format(formatArg, "诸葛亮");
		System.out.println(info);
	}

	/**
	 * split方法：字符串分割，分隔符使用正则表达式匹配
	 */
	public static void testSplit() {
		// \\s：空白字符[ \t\n\x0B\f\r ]。注意：其中的反斜杠使用了2个
		String[] result = "this is a test".split("\\s");
		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}

}
