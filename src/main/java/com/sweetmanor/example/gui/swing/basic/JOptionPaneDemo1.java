package com.sweetmanor.example.gui.swing.basic;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 交互对话框示例：使用JOptionPane实现各种对话框
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JOptionPaneDemo1 {
	JFrame frame = new JFrame("交互对话框示例");

	// 定义6个面板，分别用于定义对话框的几种选项
	private ButtonPanel messagePanel;// 消息内容面板
	private ButtonPanel messageTypePanel;// 消息类型面板
	private ButtonPanel msgPanel;// 消息对话框面板
	private ButtonPanel confirmPanel;// 确认对话框面板
	private ButtonPanel optionsPanel;// 选项对话框面板
	private ButtonPanel inputPanel;// 输入对话框面板

	private String msgStr = "消息区内容";// 消息内容：字符串
	private Icon msgIcon = new ImageIcon(CommonParam.resourcePath
			+ "ico/heart.png");// 消息内容：图标
	private Object msgObj = new Date();// 消息内容：普遍对象
	private Component msgButton = new JButton("组件消息");// 消息内容：组件对象

	public void init() {
		Box box = new Box(BoxLayout.Y_AXIS);
		frame.add(box);

		// 顶部区域
		JPanel top = new JPanel();
		top.setBorder(new TitledBorder(new EtchedBorder(), "对话框的通用选项",
				TitledBorder.CENTER, TitledBorder.TOP));
		top.setLayout(new GridLayout(1, 2));
		box.add(top);

		messageTypePanel = new ButtonPanel("选择消息的类型", new String[] {
				"ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MESSAGE",
				"QUESTION_MESSAGE", "PLAIN_MESSAGE" });// 创建消息类型面板
		top.add(messageTypePanel);
		messagePanel = new ButtonPanel("选择消息内容的类型", new String[] { "字符串信息",
				"图标消息", "组件消息", "普通对象消息", "Object[]消息" });// 创建消息内容面板
		top.add(messagePanel);

		// 底部区域
		JPanel bottom = new JPanel();
		bottom.setBorder(new TitledBorder(new EtchedBorder(), "弹出不同的对话框",
				TitledBorder.CENTER, TitledBorder.TOP));
		bottom.setLayout(new GridLayout(1, 4));
		box.add(bottom);

		ShowAction btListener = new ShowAction();// 按钮监听器

		// 组建消息对话框面板
		JButton btMessage = new JButton("消息对话框");
		btMessage.addActionListener(btListener);
		msgPanel = new ButtonPanel("消息对话框", null);
		msgPanel.add(btMessage);
		bottom.add(msgPanel);

		// 组建确认对话框面板
		JButton btConfrim = new JButton("确认对话框");
		btConfrim.addActionListener(new ShowAction());
		confirmPanel = new ButtonPanel("确认对话框", new String[] {
				"DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION",
				"OK_CANCEL_OPTION" });// 包含四个选项
		confirmPanel.add(btConfrim);
		bottom.add(confirmPanel);

		// 组建输入对话框面板
		JButton btInput = new JButton("输入对话框");
		btInput.addActionListener(new ShowAction());
		inputPanel = new ButtonPanel("输入对话框",
				new String[] { "单行文本框", "下拉列表选择框" });// 包含两个选项
		inputPanel.add(btInput);
		bottom.add(inputPanel);

		// 组建选项对话框面板
		JButton btOption = new JButton("选项对话框");
		btOption.addActionListener(new ShowAction());
		optionsPanel = new ButtonPanel("选项对话框", new String[] { "字符串选项", "图标选项",
				"对象选项" });// 包含三个选项
		optionsPanel.add(btOption);
		bottom.add(optionsPanel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * 返回确认对话框的按钮选项
	 */
	private int getOptionType() {
		if ("DEFAULT_OPTION".equals(confirmPanel.getSelection())) {
			return JOptionPane.DEFAULT_OPTION;// 默认选项将只返回“确定”按钮
		} else if ("YES_NO_OPTION".equals(confirmPanel.getSelection())) {
			return JOptionPane.YES_NO_OPTION;
		} else if ("YES_NO_CANCEL_OPTION".equals(confirmPanel.getSelection())) {
			return JOptionPane.YES_NO_CANCEL_OPTION;
		} else {
			return JOptionPane.OK_CANCEL_OPTION;
		}
	}

	/**
	 * 返回消息内容
	 */
	private Object getMessage() {
		if ("字符串信息".equals(messagePanel.getSelection())) {
			return msgStr;
		} else if ("图标消息".equals(messagePanel.getSelection())) {
			return msgIcon;
		} else if ("组件消息".equals(messagePanel.getSelection())) {
			return msgButton;
		} else if ("普通对象消息".equals(messagePanel.getSelection())) {
			return msgObj;
		} else {
			return new Object[] { msgStr, msgIcon, msgButton, msgObj };
		}
	}

	/**
	 * 返回消息的类型（决定图标区的图标）
	 */
	private int getDialogType() {
		if ("ERROR_MESSAGE".equals(messageTypePanel.getSelection())) {
			return JOptionPane.ERROR_MESSAGE;
		} else if ("INFORMATION_MESSAGE"
				.equals(messageTypePanel.getSelection())) {
			return JOptionPane.INFORMATION_MESSAGE;
		} else if ("WARNING_MESSAGE".equals(messageTypePanel.getSelection())) {
			return JOptionPane.WARNING_MESSAGE;
		} else if ("QUESTION_MESSAGE".equals(messageTypePanel.getSelection())) {
			return JOptionPane.QUESTION_MESSAGE;
		} else {
			return JOptionPane.PLAIN_MESSAGE;
		}
	}

	/**
	 * 返回选项对话框的选项
	 */
	private Object[] getOptions() {
		if ("字符串选项".equals(optionsPanel.getSelection())) {
			return new String[] { "a", "b", "c", "d" };
		} else if ("图标选项".equals(optionsPanel.getSelection())) {
			return new Icon[] {
					new ImageIcon(CommonParam.resourcePath + "ico/1.gif"),
					new ImageIcon(CommonParam.resourcePath + "ico/2.gif"),
					new ImageIcon(CommonParam.resourcePath + "ico/3.gif"),
					new ImageIcon(CommonParam.resourcePath + "ico/4.gif") };
		} else {
			return new Object[] { new Date(), new Date(), new Date() };
		}
	}

	public static void main(String[] args) {
		new JOptionPaneDemo1().init();
	}

	// 按钮监听器：该监听器其实是本示例的重点，将单独提取到JOptionPaneDemo2中进行简单使用示例
	class ShowAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ("确认对话框".equals(e.getActionCommand())) {
				JOptionPane.showConfirmDialog(frame, getMessage(), "确认对话框",
						getOptionType(), getDialogType());
			} else if ("输入对话框".equals(e.getActionCommand())) {
				if ("单行文本框".equals(inputPanel.getSelection())) {
					JOptionPane.showInputDialog(frame, getMessage(), "输入对话框",
							getDialogType());
				} else {
					JOptionPane.showInputDialog(frame, getMessage(), "输入对话框",
							getDialogType(), null, new String[] {
									"轻量级JavaEE企业应用开发", "Struts2权威指南" },
							"Struts2权威指南");
				}
			} else if ("消息对话框".equals(e.getActionCommand())) {
				JOptionPane.showMessageDialog(frame, getMessage(), "消息对话框",
						getDialogType());
			} else if ("选项对话框".equals(e.getActionCommand())) {
				JOptionPane.showOptionDialog(frame, getMessage(), "选项对话框",
						getOptionType(), getDialogType(), null, getOptions(),
						"b");
			}
		}
	}

	// 可指定标题的自定义单选按钮组组件
	class ButtonPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private ButtonGroup btGroup = new ButtonGroup();// 单选按钮组

		public ButtonPanel(String title, String[] options) {
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), title));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			for (int i = 0; options != null && i < options.length; i++) {
				JRadioButton rbItem = new JRadioButton(options[i]);
				add(rbItem);
				btGroup.add(rbItem);
				// 疑问：单独使用时不需要指定ActionCommand属性，但此处不加此语句返回值不正确，原因未知。
				rbItem.setActionCommand(options[i]);
				rbItem.setSelected(i == 0);
			}
		}

		public String getSelection() {
			return btGroup.getSelection().getActionCommand();
		}
	}

}
