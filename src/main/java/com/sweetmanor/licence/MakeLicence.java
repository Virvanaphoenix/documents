package com.sweetmanor.licence;

import java.security.interfaces.RSAPrivateKey;

import com.sweetmanor.util.ObjectAccessUtil;
import com.sweetmanor.util.SecurityUtil;

public class MakeLicence {

	public static void main(String[] args) {
		RSAPrivateKey pri_key = (RSAPrivateKey) ObjectAccessUtil
				.readFromFile(".\\key\\rsa_pri");

		byte[] b_key = SecurityUtil.RSAEncode("971114000005", pri_key);
		byte[] b_deadline = SecurityUtil.RSAEncode("20140720", pri_key);
		byte[] b_hasLimit = SecurityUtil.RSAEncode("yes", pri_key);

		Licence licence = new Licence();
		licence.setKey(b_key);
		licence.setDeadline(b_deadline);
		licence.setHasLimit(b_hasLimit);

		ObjectAccessUtil.writeToFile(licence, ".\\licence");

	}

}
