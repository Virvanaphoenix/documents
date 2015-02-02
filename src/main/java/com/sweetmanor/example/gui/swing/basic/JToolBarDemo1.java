package com.sweetmanor.example.gui.swing.basic;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 工具条示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JToolBarDemo1 {
	JFrame frame = new JFrame("工具条示例");
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public void init() {
		final JTextArea ta = new JTextArea(6, 35);
		frame.add(new JScrollPane(ta));

		// 创建“粘贴”Action，用于创建普通按钮、菜单项、工具菜单
		final Action pasteAction = new AbstractAction("粘贴", new ImageIcon(
				CommonParam.resourcePath + "ico/paste.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果剪贴板中包含字符串
				if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
					try {
						String content = (String) clipboard
								.getData(DataFlavor.stringFlavor);// 获取剪贴板内容
						ta.replaceRange(content, ta.getSelectionStart(),
								ta.getSelectionEnd());// 替换选中内容
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		pasteAction.setEnabled(false);// 默认“粘贴”Action不激活

		// 创建“复制”Action
		Action copyAction = new AbstractAction("复制", new ImageIcon(
				CommonParam.resourcePath + "ico/copy.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection contents = new StringSelection(
						ta.getSelectedText());// 以选中内容创建字符传递对象
				clipboard.setContents(contents, null);// 将StringSelection对象放入系统剪贴板
				if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor))
					pasteAction.setEnabled(true);// 激活“粘贴”Action，此处的激活以后将一直可用，示例中不再进行优化
			}
		};

		// 以Action创建普通按钮
		JPanel btPanel = new JPanel();
		frame.add(btPanel, BorderLayout.SOUTH);
		JButton btCopy = new JButton(copyAction);
		btPanel.add(btCopy);
		JButton btPaste = new JButton(pasteAction);
		btPanel.add(btPaste);

		// 以Action创建菜单，直接添加Action到菜单，菜单负责生成菜单项
		JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		JMenu edit = new JMenu("编辑");
		edit.add(copyAction);
		edit.add(pasteAction);
		mb.add(edit);

		// 以Action创建工具条，直接添加Action到工具条中，工具条负责生成工具按钮
		JToolBar tb = new JToolBar();
		tb.setMargin(new Insets(20, 10, 5, 30));// 设置工具条按钮四周边距
		tb.setFloatable(false);// 设置工具条不可浮动，工具条默认是可浮动的。
		frame.add(tb, BorderLayout.NORTH);// 添加工具条到窗体
		tb.add(copyAction);
		tb.addSeparator();// 添加分隔条
		tb.add(pasteAction);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new JToolBarDemo1().init();
	}

}
