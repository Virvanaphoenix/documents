package com.sweetmanor.example.gui.swing;

import javax.swing.UIManager;

/**
 * 当前可用的PLAF：Swing组件显示风格，相当于主题
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class AllLookAndFeel {

	public static void main(String[] args) {
		System.out.println("当前可用的LAF：");
		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels())
			System.out.println(info.getName() + "--->\t" + info);
	}

}
