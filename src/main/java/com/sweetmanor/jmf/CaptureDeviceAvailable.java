package com.sweetmanor.jmf;

import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;

/**
 * 获取所有可用的多媒体设备。
 */
public class CaptureDeviceAvailable {

	public static void main(String[] args) {
		Vector deviceList = CaptureDeviceManager.getDeviceList(null);// 获得捕获设备列表
		System.out.println("找到多媒体设备:" + deviceList.size());
		for (int i = 0; i < deviceList.size(); i++) {
			System.out.println("多媒体设备" + i + ":\n" + deviceList.get(i));
		}
		CaptureDeviceInfo webcam = CaptureDeviceManager
				.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");// 单独获取摄像头
		System.out.println("摄像头：\n" + webcam);
	}

}
