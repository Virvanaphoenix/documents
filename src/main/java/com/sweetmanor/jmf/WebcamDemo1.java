package com.sweetmanor.jmf;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.CaptureDeviceInfo;
import javax.media.protocol.DataSource;

import jmapps.jmstudio.CaptureDialog;
import jmapps.ui.PlayerFrame;
import jmapps.util.JMFUtils;

import com.sweetmanor.util.FrameUtil;

/**
 * 继承PlayerFrame实现简单的摄像头实时监控
 */
public class WebcamDemo1 extends PlayerFrame {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;

	public WebcamDemo1() {
		super(null, "摄像头监控程序");
	}

	public void init() {
		CaptureDialog dialogCapture = new CaptureDialog(this, null);// 创建设备选择对话框
		dialogCapture.setVisible(true);// 显示选择对话框
		// 如果单击了“取消”按钮，则退出程序
		if (dialogCapture.getAction().equals(CaptureDialog.ACTION_CANCEL)) {
			System.exit(0);
		}
		// 获取视频和音频设备，此处其实可以直接根据字符串进行初始化，但对话框选择更具有通用性
		CaptureDeviceInfo videoDevice = dialogCapture.getVideoDevice();
		CaptureDeviceInfo audioDevice = dialogCapture.getAudioDevice();
		dataSource = JMFUtils.createCaptureDataSource(audioDevice.getName(),
				dialogCapture.getAudioFormat(), videoDevice.getName(),
				dialogCapture.getVideoFormat());// 以视频和音频设备创建数据源
		try {
			dataSource.connect();// 连接数据源
		} catch (IOException e) {
			e.printStackTrace();
		}
		open(dataSource);// 打开数据源
		mediaPlayerCurrent.start();// 启动播放
		setVisible(true);
	}

	public static void main(String[] args) {
		new WebcamDemo1().init();
	}

	// 窗体关闭时回收资源，其实应该写在finally里面
	@Override
	public void windowClosing(WindowEvent e) {
		mediaPlayerCurrent.close();// 关闭当前播放器
		dataSource.disconnect();// 关闭数据源
		System.exit(0);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		FrameUtil.center(this);// 组件重绘时窗体居中
	}

}
