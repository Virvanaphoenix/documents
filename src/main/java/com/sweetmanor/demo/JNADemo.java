package com.sweetmanor.demo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * Java调用本地C库的printf函数打印Hello World
 * Win7系统下所有系统已注册dll文件存放在注册表项：HKEY_LOCAL_MACHINE\
 * SYSTEM\ControlSet001\Control\Session Manager\KnownDLLs
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JNADemo {

	/**
	 * 内部接口包装msvcrt.dll的Java代理
	 */
	public interface CLibrary extends Library {
		// 加载DLL文件的代理类，此处的msvcrt是Windows平台的已注册动态链接库，未注册需要使用文件全名
		// 此处未使用static关键字，但从main方法中可以直接调用来看是静态常量
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
				(Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

		// 指定函数的参数类型，要与DLL文件的函数格式一致
		void printf(String format, Object... args);
	}

	public static void main(String[] args) {
		CLibrary.INSTANCE.printf("这是由系统的Printf函数打印的：\n");
		CLibrary.INSTANCE.printf("Hello World\n");
		for (int i = 0; i < args.length; i++)
			CLibrary.INSTANCE.printf("Argument %d: %s/n", i, args[i]);
	}

}
