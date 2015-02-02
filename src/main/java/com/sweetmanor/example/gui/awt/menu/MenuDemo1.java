package com.sweetmanor.example.gui.awt.menu;

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 菜单示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class MenuDemo1 {
	JFrame frame = new JFrame("菜单示例");

	public void init() {
		final TextArea ta = new TextArea(6, 20);
		frame.add(ta);

		// 以匿名内部类形式创建菜单监听器
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				ta.append("单击" + cmd + "菜单\n");
				if (cmd.equals("退出"))
					System.exit(0);
			}
		};

		MenuBar menuBar = new MenuBar();// 菜单栏
		frame.setMenuBar(menuBar);

		// 文件菜单
		Menu fileMenu = new Menu("文件");
		menuBar.add(fileMenu);

		MenuItem newMenuItem = new MenuItem("新建");
		newMenuItem.addActionListener(menuListener);
		fileMenu.add(newMenuItem);

		MenuItem saveMenuItem = new MenuItem("保存");
		saveMenuItem.addActionListener(menuListener);
		fileMenu.add(saveMenuItem);

		MenuItem exitMenuItem = new MenuItem("退出", new MenuShortcut(
				KeyEvent.VK_X)); // 指定Ctrl+X快捷键
		exitMenuItem.addActionListener(menuListener);
		fileMenu.add(exitMenuItem);

		// 编辑菜单
		Menu editMenu = new Menu("编辑");
		menuBar.add(editMenu);

		CheckboxMenuItem autoWrap = new CheckboxMenuItem("自动换行"); // 复选框菜单项
		autoWrap.addActionListener(menuListener);// 复选框菜单不支持此监听器，应该使用状态改变监听器
		editMenu.add(autoWrap);

		editMenu.addSeparator(); // 添加分隔菜单项

		MenuItem copyMenuItem = new MenuItem("复制");
		copyMenuItem.addActionListener(menuListener);
		editMenu.add(copyMenuItem);

		MenuItem pasteMenuItem = new MenuItem("粘贴");
		pasteMenuItem.addActionListener(menuListener);
		editMenu.add(pasteMenuItem);

		editMenu.add(new MenuItem("-")); // 添加分隔菜单项的另一种方式

		Menu format = new Menu("格式"); // 创建一个二级菜单
		editMenu.add(format);

		MenuItem commentMenuItem = new MenuItem("注释", new MenuShortcut(
				KeyEvent.VK_SLASH, true)); // 指定Ctrl+Shift+/快捷键
		commentMenuItem.addActionListener(menuListener);
		format.add(commentMenuItem);

		MenuItem cancelMenuItem = new MenuItem("取消注释");
		cancelMenuItem.addActionListener(menuListener);
		format.add(cancelMenuItem);

		frame.setSize(600, 400);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MenuDemo1().init();
	}

}
