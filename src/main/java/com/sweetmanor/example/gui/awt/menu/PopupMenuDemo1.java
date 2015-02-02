package com.sweetmanor.example.gui.awt.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 右键弹出菜单示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class PopupMenuDemo1 {
	JFrame frame = new JFrame("弹出菜单示例");

	public void init() {
		final TextArea ta = new TextArea(6, 20);
		frame.add(ta, BorderLayout.NORTH);

		// 定义菜单事件监听器
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				ta.append("单击" + cmd + "菜单\n");
				if (cmd.equals("退出"))
					System.exit(0);
			}
		};

		final PopupMenu popMenu = new PopupMenu(); // 右键弹出菜单

		MenuItem newMenuItem = new MenuItem("新建");
		newMenuItem.addActionListener(menuListener);
		popMenu.add(newMenuItem);

		MenuItem saveMenuItem = new MenuItem("保存");
		saveMenuItem.addActionListener(menuListener);
		popMenu.add(saveMenuItem);

		MenuItem exitMenuItem = new MenuItem("退出", new MenuShortcut(
				KeyEvent.VK_X)); // 疑问：弹出菜单的快捷键好像无效
		exitMenuItem.addActionListener(menuListener);
		popMenu.add(exitMenuItem);

		final Panel panel = new Panel();// 关联弹出菜单的面板组件
		frame.add(panel);
		panel.setPreferredSize(new Dimension(400, 200));
		panel.add(popMenu); // 在面板上添加弹出菜单
		panel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// 返回此鼠标事件是否为该平台的弹出菜单触发事件。
				// JDK API注：在不同系统上弹出菜单的触发方式不同。
				// 因此，为了正确实现跨平台功能，在 mouseReleased 和 mousePressed
				// 中都应检查isPopupTrigger。
				if (e.isPopupTrigger())
					popMenu.show(panel, e.getX(), e.getY());
			}
		});

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new PopupMenuDemo1().init();
	}

}
