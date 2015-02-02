package com.sweetmanor.example.io;

import java.util.prefs.Preferences;

/**
 * 
 * @author Stars
 * 
 *         Preferences允许应用程序存储和获取用户和系统首选项以及配置数据，在Windows即存储在注册表中
 *         userRoot：HKEY_CURRENT_USER\Software\JavaSoft\Prefs
 * 
 *         systemRoot：HKEY_LOCAL_MACHINE/SOFTWARE/JavaSoft/Prefs
 * 
 */
public class PreferencesDemo1 {

	public static void main(String[] args) {
		Preferences userNode = Preferences
				.userNodeForPackage(PreferencesDemo1.class);
		userNode.put("user", "ijlhjj");
		String user = userNode.get("user", "获取不到值");
		System.out.println(user);
	}

}
