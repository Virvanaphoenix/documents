package com.sweetmanor.example.javademo.immutable;

/**
 * 不可变类示例：1)private final修饰；2)构造器中初始化；3)只提供get方法。 不可变类的Feild必须也是不可变类型，否则需要做保护
 * 
 * @version 1.0 2014-11-20
 * @author ijlhjj
 */
public class ImmutableClassDemo1_Address {
	private final String detail;// String类型是不可变类
	private final String postCode;

	// 无参构造器一般不提供，除非有系统默认值
	public ImmutableClassDemo1_Address() {
		this.detail = "";
		this.postCode = "";
	}

	public ImmutableClassDemo1_Address(String detail, String postCode) {
		this.detail = detail;
		this.postCode = postCode;
	}

	public String getDetail() {
		return detail;
	}

	public String getPostCode() {
		return postCode;
	}

}
