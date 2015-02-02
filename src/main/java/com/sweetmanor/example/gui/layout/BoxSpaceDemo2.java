package com.sweetmanor.example.gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

import javax.swing.Box;

import com.sweetmanor.util.FrameUtil;

/**
 * BoxLayout示例：注意和Demo1的区别，Button组件填满了整个容器空间，JButton控件的大小是固定的，没有扩展
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BoxSpaceDemo2 {
	Frame frame = new Frame("BoxLayout示例");

	public void init() {
		Box horizontal = Box.createHorizontalBox(); // 水平方向的盒型布局
		frame.add(horizontal, BorderLayout.NORTH);
		horizontal.add(new Button("水平按钮一"));
		horizontal.add(Box.createHorizontalGlue()); // 水平间距，可在两个方向上扩充
		horizontal.add(new Button("水平按钮二"));
		horizontal.add(Box.createHorizontalStrut(10)); // 水平间距，宽度固定，只在垂直方向扩充
		horizontal.add(new Button("水平按钮三"));

		Box vertical = Box.createVerticalBox(); // 垂直方向的盒型布局
		frame.add(vertical);
		vertical.add(new Button("垂直按钮一"));
		vertical.add(Box.createVerticalGlue());// 垂直间距，可在两个方向扩充
		vertical.add(new Button("垂直按钮二"));
		vertical.add(Box.createVerticalStrut(10)); // 垂直间距，高度固定，只在水平方向扩充
		vertical.add(new Button("垂直按钮三"));

		frame.pack();
		FrameUtil.center(frame);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BoxSpaceDemo2().init();
	}

}
