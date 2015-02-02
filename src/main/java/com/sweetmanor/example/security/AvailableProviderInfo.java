package com.sweetmanor.example.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * 打印所有可用的Provider（安全提供者）的信息
 */
public class AvailableProviderInfo {

	public static void main(String[] args) {
		for (Provider p : Security.getProviders()) {
			System.out.println(p);// 打印当前提供者的信息
			for (Map.Entry<Object, Object> entry : p.entrySet()) {
				System.out.println("\t" + entry.getKey());// 打印提供者的键值
			}
		}
	}

}
