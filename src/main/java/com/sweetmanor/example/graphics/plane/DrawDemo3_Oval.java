package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawDemo3_Oval {
	Frame f = new Frame("绘制椭圆");
	// 定义外接矩形的左上角坐标
	private final int preX = 50;
	private final int preY = 50;
	// 定义外接矩形的宽度和高度
	private final int RECT_WIDTH = 400;
	private final int RECT_HEIGHT = 300;
	MyCanvas canvas = new MyCanvas();

	public void init() {
		f.add(canvas);
		f.setBounds(300, 150, 500, 450);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DrawDemo3_Oval().init();
	}

	class MyCanvas extends JPanel {
		private static final long serialVersionUID = -8184564538351057457L;

		public void paint(Graphics g) {
			// 设置外接矩形的边框颜色为黑色
			g.setColor(new Color(0, 0, 0));
			// 绘制外接矩形
			g.drawRect(preX, preY, RECT_WIDTH, RECT_HEIGHT);
			// 绘制矩形的中线及对角线
			g.drawLine(preX, preY, preX + RECT_WIDTH, preY + RECT_HEIGHT);
			g.drawLine(preX, preY + RECT_HEIGHT / 2, preX + RECT_WIDTH, preY
					+ RECT_HEIGHT / 2);
			g.drawLine(preX, preY + RECT_HEIGHT, preX + RECT_WIDTH, preY);
			g.drawLine(preX + RECT_WIDTH / 2, preY, preX + RECT_WIDTH / 2, preY
					+ RECT_HEIGHT);

			// 设置椭圆的绘制颜色为红色
			g.setColor(new Color(250, 0, 0));
			// 绘制椭圆
			g.drawOval(preX, preY, RECT_WIDTH, RECT_HEIGHT);
			// 绘制圆形
			g.fillOval(preX + 100, preY + 50, RECT_HEIGHT - 100,
					RECT_HEIGHT - 100);

		}
	}

}
