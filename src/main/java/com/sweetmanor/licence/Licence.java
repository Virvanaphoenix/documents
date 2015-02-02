package com.sweetmanor.licence;

import java.io.Serializable;

public class Licence implements Serializable {
	private static final long serialVersionUID = 8553391412426780361L;
	private byte[] hasLimit;
	private byte[] key; // 序列号
	private byte[] deadline;// 适用截止日期

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getDeadline() {
		return deadline;
	}

	public void setDeadline(byte[] deadline) {
		this.deadline = deadline;
	}

	public byte[] getHasLimit() {
		return hasLimit;
	}

	public void setHasLimit(byte[] hasLimit) {
		this.hasLimit = hasLimit;
	}

}
