package com.sweetmanor.example.gui.event;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.sweetmanor.util.FrameUtil;

/**
 * 键盘驱动事件示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class KeyboardEventDemo1 {
	JFrame frame = new JFrame("键盘驱动事件示例");

	public void init() {
		final JTextArea ta = new JTextArea(5, 30);
		frame.add(ta);

		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.SOUTH);

		final JTextField tf = new JTextField(15);
		panel.add(tf);
		JButton btSend = new JButton("发送");
		panel.add(btSend);

		// 创建一个发送消息的监听器
		Action sendMsg = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append(tf.getText() + "\n");
				tf.setText("");
			}
		};
		btSend.addActionListener(sendMsg);

		/*
		 * 为单行文本域绑定键盘驱动事件，将 CTRL+ENTER 键和“send”绑定，将“send”和sendMsg Action关联
		 * Swing的键盘驱动事件流程是：先将组合键绑定一个指定对象（一般是一个名称），再将指定对象绑定一个Action事件
		 */
		tf.getInputMap().put(
				KeyStroke.getKeyStroke('\n', InputEvent.CTRL_MASK), "send");
		tf.getActionMap().put("send", sendMsg);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new KeyboardEventDemo1().init();
	}

}
