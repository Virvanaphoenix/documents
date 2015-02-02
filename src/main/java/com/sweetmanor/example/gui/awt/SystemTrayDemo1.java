package com.sweetmanor.example.gui.awt;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;

import com.sweetmanor.common.CommonParam;

/**
 * 系统托盘示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SystemTrayDemo1 {

	public void init() {
		if (SystemTray.isSupported()) {
			try {
				PopupMenu popMenu = new PopupMenu();
				Image icon = ImageIO.read(new File(CommonParam.resourcePath
						+ "qq.png"));
				final TrayIcon trayIcon = new TrayIcon(icon, "图标", popMenu);// 创建系统托盘图标
				trayIcon.setImageAutoSize(true);// 自动调整图标大小
				SystemTray.getSystemTray().add(trayIcon);// 添加到系统托盘区域

				MenuItem exitMenu = new MenuItem("退出");
				exitMenu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// SystemTray.getSystemTray().remove(trayIcon);//系统托盘图标会自动删除
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
		new SystemTrayDemo1().init();
	}

}
