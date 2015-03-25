package com.sweetmanor.program;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 工具集启动类，没有界面，只在系统托盘显示
 * 
 * @version 1.0 2014-10-10
 * @author ijlhjj
 */
public class Main {

	public void init() {
		if (SystemTray.isSupported()) {
			try {
				PopupMenu popMenu = new PopupMenu();
				Image icon = ImageIO.read(new File("resource/icon1.gif"));
				final TrayIcon trayIcon = new TrayIcon(icon, "小工具集", popMenu);// 创建系统托盘图标
				trayIcon.setImageAutoSize(true);// 自动调整图标大小
				SystemTray.getSystemTray().add(trayIcon);// 添加到系统托盘区域

				MenuItem createTableFromExcel = new MenuItem("根据EXCEL文件创建表");
				createTableFromExcel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new CreateTableFromExcel();
					}
				});
				popMenu.add(createTableFromExcel);
				popMenu.addSeparator();// 增加菜单间隔

				MenuItem exitMenu = new MenuItem("退出");
				exitMenu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SystemTray.getSystemTray().remove(trayIcon);
						System.exit(0);
					}
				});
				popMenu.add(exitMenu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Main().init();
	}

}
