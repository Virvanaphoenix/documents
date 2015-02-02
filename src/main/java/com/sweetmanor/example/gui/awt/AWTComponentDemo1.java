package com.sweetmanor.example.gui.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;

import com.sweetmanor.util.FrameUtil;

/**
 * AWT基本组件使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class AWTComponentDemo1 {
	Frame frame = new Frame("AWT组件示例");

	public void init() {
		Box top = Box.createHorizontalBox();// 顶部容器
		frame.add(top);

		Box topLeft = Box.createVerticalBox();// 上左容器
		top.add(topLeft);

		TextArea ta = new TextArea(5, 20);
		topLeft.add(ta);

		Panel checkPanel = new Panel();
		topLeft.add(checkPanel);

		Choice colorChooser = new Choice(); // 颜色下拉列表框
		colorChooser.add("红色");
		colorChooser.add("绿色");
		colorChooser.add("蓝色");
		checkPanel.add(colorChooser);

		CheckboxGroup cbg = new CheckboxGroup(); // 单选框组，同一个组中的Checkbox只能有一个被选中（单选）
		Checkbox male = new Checkbox("男", cbg, true);
		Checkbox female = new Checkbox("女", cbg, false);
		checkPanel.add(male);
		checkPanel.add(female);

		Checkbox married = new Checkbox("是否已婚？", false); // 复选框组件
		checkPanel.add(married);

		List colorList = new List(6, true); // 颜色列表
		colorList.add("红色");
		colorList.add("绿色");
		colorList.add("蓝色");
		top.add(colorList);

		Panel bottom = new Panel();// 底部容器
		frame.add(bottom, BorderLayout.SOUTH);

		TextField name = new TextField(50);
		bottom.add(name);

		Button ok = new Button("确认");
		bottom.add(ok);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.pack();
		FrameUtil.center(frame);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AWTComponentDemo1().init();
	}

}
