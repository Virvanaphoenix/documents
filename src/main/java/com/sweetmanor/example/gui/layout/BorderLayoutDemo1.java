package com.sweetmanor.example.gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

/**
 * BorderLayout示例：注意BorderLayout各区域的扩展方式：NORTH、SOUTH、CENTER区域会在水平方向扩展，EAST、WEST、
 * CENTER区域会在垂直方向上扩展
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BorderLayoutDemo1 {

	public static void main(String[] args) {
		Frame frame = new Frame("BorderLayout示例");
		// BorderLayout布局：hgap:水平间距20， vgap:垂直间距5
		frame.setLayout(new BorderLayout(20, 5));

		// BorderLayout布局添加组件时需要指定区域，如果没有指定，则默认添加到CENTER区域
		frame.add(new Button("北"), BorderLayout.NORTH);
		frame.add(new Button("南"), BorderLayout.SOUTH);
		frame.add(new Button("中"));
		frame.add(new Button("东"), BorderLayout.EAST);
		frame.add(new Button("西"), BorderLayout.WEST);

		frame.setBounds(400, 200, 400, 200);
		frame.setVisible(true);
	}

}
