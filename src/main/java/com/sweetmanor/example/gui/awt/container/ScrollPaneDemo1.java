package com.sweetmanor.example.gui.awt.container;

import java.awt.Button;
import java.awt.ScrollPane;
import java.awt.TextField;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * ScrollPane示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ScrollPaneDemo1 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("ScrollPane示例");

		// 指定ScrollPane容器总是显示滚动条，ScrollPane默认采用BorderLayout布局
		ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		System.err.println(scrollPane.getLayout()); // 疑问：此处输出了null

		// BorderLayout布局默认向Center区域添加控件，所以后添加的控件会覆盖先添加的控件（重叠），以下添加的两个控件只会显示Button
		scrollPane.add(new TextField(10));
		scrollPane.add(new Button("单击我"));
		frame.add(scrollPane);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
