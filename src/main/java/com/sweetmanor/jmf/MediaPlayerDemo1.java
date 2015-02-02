package com.sweetmanor.jmf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sweetmanor.util.FrameUtil;

public class MediaPlayerDemo1 implements ControllerListener {
	JFrame frame = new JFrame("多媒体播放器");
	JMenuBar mb = new JMenuBar();
	JFileChooser fileChooser = new JFileChooser(".");// 文件选择器
	Player player;// 播放器
	Component visual;// 视频组件
	Component control;// 控制组件

	public void init() {
		JMenu file = new JMenu("文件");
		// 创建“打开”菜单
		JMenuItem openItem = new JMenuItem("打开");
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					String fileName = "file:/"
							+ fileChooser.getSelectedFile().getPath();// 获取打开文件路径
					stop();// 先尝试停止之前的播放
					play(fileName);// 播放新文件
				}
			}
		});
		file.add(openItem);
		mb.add(file);
		frame.setJMenuBar(mb);

		frame.setSize(300, 200);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * 播放指定文件
	 */
	public void play(String filePath) {
		try {
			player = Manager.createPlayer(new MediaLocator(filePath));// 以指定文件创建播放器
			player.addControllerListener(this);// 添加播放器事件监听器
			player.realize();// 识别文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止播放器播放状态
	 */
	public void stop() {
		if (player != null) {
			player.stop();
			player.close();
		}
	}

	public static void main(String[] args) {
		new MediaPlayerDemo1().init();
	}

	/**
	 * 播放器事件监听
	 */
	@Override
	public void controllerUpdate(ControllerEvent event) {
		// 文件识别完成状态
		if (event instanceof RealizeCompleteEvent) {
			// 获取视频组件加入窗体
			if ((visual = player.getVisualComponent()) != null) {
				frame.add(visual);
			}
			// 获取控制组件加入窗体
			if ((control = player.getControlPanelComponent()) != null) {
				frame.add(control, BorderLayout.SOUTH);
			}
			frame.pack();// 调整窗体到合适大小
			player.prefetch();// 准备文件资源
		}
		// 文件资源准备完成
		else if (event instanceof PrefetchCompleteEvent) {
			player.start();// 开始播放
		}
		// 文件播放结束，重新循环播放
		else if (event instanceof EndOfMediaEvent) {
			player.setMediaTime(new Time(0));
			player.start();
		}
	}

}
