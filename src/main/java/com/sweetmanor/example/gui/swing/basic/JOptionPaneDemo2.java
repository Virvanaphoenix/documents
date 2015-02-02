package com.sweetmanor.example.gui.swing.basic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 交互对话框示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JOptionPaneDemo2 {
	JFrame frame = new JFrame("交互对话框示例");

	public void init() {
		final JTextArea ta = new JTextArea(6, 30);
		frame.add(ta);

		JPanel btPanel = new JPanel();
		frame.add(btPanel, BorderLayout.SOUTH);

		// 消息对话框示例
		JButton btMessage = new JButton("消息对话框");
		btMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "操作成功");// 消息对话框没有返回值
				// 以下方法可自定义设置标题和图标
				// JOptionPane.showMessageDialog(frame, "操作成功", "消息提示",
				// JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btPanel.add(btMessage);

		// 确认对话框示例
		JButton btConfrim = new JButton("确认对话框");
		btConfrim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,
						"您确认删除所选文件吗？", "删除确认", JOptionPane.OK_CANCEL_OPTION);// 确认对话框返回用户单击的按钮
				// 以下方法可自定义设置图标
				// int result = JOptionPane.showConfirmDialog(frame,
				// "您确认删除所选文件吗？", "删除确认", JOptionPane.OK_CANCEL_OPTION,
				// JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.OK_OPTION)
					ta.append("文件已删除\n");
				else
					ta.append("用户取消了删除文件\n");
			}
		});
		btPanel.add(btConfrim);

		// 输入对话框示例
		JButton btInput = new JButton("输入对话框");
		btInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 输入对话框返回用户输入的字符串（或选择的选项内容），如果没有输入则返回null。输入对话框不能同时设置图标和初始值，如果要同时设置只能使用选项对话框代替
				String name = JOptionPane.showInputDialog(frame, "请输入姓名：",
						"姓名输入", JOptionPane.PLAIN_MESSAGE);
				if (name != null)
					ta.append("输入的姓名为：" + name + "\n");
				else if (name == null)
					ta.append("用户没有输入姓名！\n");
			}
		});
		btPanel.add(btInput);

		// 选项对话框示例，选项对话框是功能最丰富的，可以代替其他几类对话框，只是使用时有点复杂
		JButton btOption = new JButton("选项对话框");
		btOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 参数说明：message是Object类型的，这个参数既可以是一个字符串，也可以是一个JPanel。
				 * 当它是一个JPanel容器时
				 * ，他的内部可以包含几乎任意内容。（但他包含一个JTextField时如何获取其中的输入内容呢？还不知道）
				 * optionType是在用户没有设置options参数时默认使用的按钮
				 * messageType是在用户没有设置icon参数时默认使用的图标
				 * initialValue是初始化时光标默认在那个按钮上，这时直接按“Enter“键将相当于单击此按钮
				 */
				// 选项对话框返回用户单击的按钮序号，尤其在自定义按钮组时要特别注意。
				int index = JOptionPane.showOptionDialog(frame,
						"这个对话框的内容是最丰富的", "选项对话框",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.ERROR_MESSAGE, new ImageIcon(
								CommonParam.resourcePath + "ico/heart.png"),
						new String[] { "赞成", "反对", "不管我事", "抢地主" }, "抢地主");
				switch (index) {
				case 0:
					ta.append("用户赞成\n");
					break;
				case 1:
					ta.append("用户反对\n");
					break;
				case 2:
					ta.append("用户不管\n");
					break;
				case 3:
					ta.append("用户傻了\n");
					break;
				default:
					ta.append("难道他没选吗?\n");
				}
			}
		});
		btPanel.add(btOption);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new JOptionPaneDemo2().init();
	}

}
