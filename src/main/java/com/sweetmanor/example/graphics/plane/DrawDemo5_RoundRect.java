package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawDemo5_RoundRect {

	Frame f = new Frame("绘制椭圆");
	// 定义外接矩形的左上角坐标
	private final int preX = 50;
	private final int preY = 50;
	// 定义外接矩形的宽度和高度
	private final int RECT_WIDTH = 400;
	private final int RECT_HEIGHT = 300;
	// 定义圆角弧度的水平直径和垂直直径
	private final int arcWidth = 200;
	private final int arcHeight = 50;
	MyCanvas canvas = new MyCanvas();

	public void init() {
		f.add(canvas);
		f.setBounds(300, 150, 500, 450);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DrawDemo5_RoundRect().init();
	}

	class MyCanvas extends JPanel {
		private static final long serialVersionUID = -8184564538351057457L;

		public void paint(Graphics g) {
			// 设置外接矩形的边框颜色为黑色
			g.setColor(new Color(0, 0, 0));
			// 绘制外接矩形
			g.drawRect(preX, preY, RECT_WIDTH, RECT_HEIGHT);

			// 设置绘制颜色为红色
			g.setColor(new Color(250, 0, 0));
			// 绘制圆角矩形
			g.drawRoundRect(preX, preY, RECT_WIDTH, RECT_HEIGHT, arcWidth,
					arcHeight);
		}
	}

}
