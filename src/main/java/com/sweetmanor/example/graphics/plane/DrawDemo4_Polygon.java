package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class DrawDemo4_Polygon {
	Frame f = new Frame("绘制五角星");
	// 定义外接矩形的宽度和高度
	private final int RECT_SIZE = 400;

	// 定义外接矩形的左上角坐标x1、y1，其他各坐标为方便引用
	private final int x1 = 50;
	private final int y1 = 50;
	private int xCenter = x1 + RECT_SIZE / 2;
	private int yCenter = y1 + RECT_SIZE / 2;
	private int x2 = x1 + RECT_SIZE;
	private int y2 = y1 + RECT_SIZE;
	// 定义画点的偏移量
	private int offset = 5;
	MyCanvas canvas = new MyCanvas();

	public void init() {
		f.add(canvas);
		f.setBounds(300, 100, 550, 550);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DrawDemo4_Polygon().init();
	}

	// 角度转换为弧度函数
	private double getRadian(double d) {
		return d * Math.PI / 180.0;
	}

	class MyCanvas extends JPanel {
		private static final long serialVersionUID = -8184564538351057457L;

		public void paint(Graphics g) {
			// 设置外接矩形的边框颜色为黑色
			g.setColor(new Color(0, 0, 0));
			// 绘制外接矩形
			g.drawRect(x1, y1, RECT_SIZE, RECT_SIZE);
			// 绘制矩形的中线及对角线
			g.drawLine(x1, y1, x2, y2);
			g.drawLine(x1, yCenter, x2, yCenter);
			g.drawLine(x1, y2, x2, y1);
			g.drawLine(xCenter, y1, xCenter, y2);
			// 绘制圆形
			g.drawOval(x1, y1, RECT_SIZE, RECT_SIZE);

			// 设置椭圆的绘制颜色为红色
			g.setColor(new Color(250, 0, 0));

			// 五角星的5个顶点和5个折点，正上方为第一个，顺时针依次排列
			// 第1个凸点
			int salientPoint1X = xCenter;
			int salientPoint1Y = y1;
			g.fillOval(salientPoint1X - offset, salientPoint1Y - offset,
					offset * 2, offset * 2);
			// 第2个凸点
			int salientPoint2X = (int) (xCenter + (RECT_SIZE / 2)
					* Math.cos(getRadian(18)));
			int salientPoint2Y = (int) (yCenter - (RECT_SIZE / 2)
					* Math.sin(getRadian(18)));
			g.fillOval(salientPoint2X - offset, salientPoint2Y - offset,
					offset * 2, offset * 2);
			// 第3个凸点
			int salientPoint3X = (int) (xCenter + (RECT_SIZE / 2)
					* Math.cos(getRadian(54)));
			int salientPoint3Y = (int) (yCenter + (RECT_SIZE / 2)
					* Math.sin(getRadian(54)));
			g.fillOval(salientPoint3X - offset, salientPoint3Y - offset,
					offset * 2, offset * 2);
			// 第4个凸点
			int salientPoint4X = (int) (xCenter - (RECT_SIZE / 2)
					* Math.cos(getRadian(54)));
			int salientPoint4Y = salientPoint3Y;
			g.fillOval(salientPoint4X - offset, salientPoint4Y - offset,
					offset * 2, offset * 2);
			// 第5个凸点
			int salientPoint5X = (int) (xCenter - (RECT_SIZE / 2)
					* Math.cos(getRadian(18)));
			int salientPoint5Y = salientPoint2Y;
			g.fillOval(salientPoint5X - offset, salientPoint5Y - offset,
					offset * 2, offset * 2);

			// 第1个凹点
			int pit1X = (int) (xCenter + (RECT_SIZE / 2)
					* Math.tan((getRadian(18))) * (1 - Math.sin(getRadian(18))));
			int pit1Y = salientPoint2Y;
			g.fillOval(pit1X - offset, pit1Y - offset, offset * 2, offset * 2);
			// 第2个凹点
			int pit2X = (int) (xCenter + (RECT_SIZE / 2)
					* Math.sin(getRadian(18)) / Math.cos(getRadian(36))
					* Math.cos(getRadian(18)));
			int pit2Y = (int) (xCenter + (RECT_SIZE / 2)
					* Math.sin(getRadian(18)) / Math.cos(getRadian(36))
					* Math.sin(getRadian(18)));
			g.fillOval(pit2X - offset, pit2Y - offset, offset * 2, offset * 2);
			// 第3个凹点
			int pit3X = xCenter;
			int pit3Y = (int) (yCenter + (RECT_SIZE / 2)
					* Math.sin(getRadian(18)) / Math.cos(getRadian(36)));
			g.fillOval(pit3X - offset, pit3Y - offset, offset * 2, offset * 2);
			// 第4个凹点
			int pit4X = (int) (xCenter - (RECT_SIZE / 2)
					* Math.sin(getRadian(18)) / Math.cos(getRadian(36))
					* Math.cos(getRadian(18)));
			int pit4Y = pit2Y;
			g.fillOval(pit4X - offset, pit4Y - offset, offset * 2, offset * 2);
			// 第5个凹点
			int pit5X = (int) (xCenter - (RECT_SIZE / 2)
					* Math.tan((getRadian(18))) * (1 - Math.sin(getRadian(18))));
			int pit5Y = pit1Y;
			g.fillOval(pit5X - offset, pit5Y - offset, offset * 2, offset * 2);

			int[] xPoints = { salientPoint1X, pit1X, salientPoint2X, pit2X,
					salientPoint3X, pit3X, salientPoint4X, pit4X,
					salientPoint5X, pit5X };
			int[] yPoints = { salientPoint1Y, pit1Y, salientPoint2Y, pit2Y,
					salientPoint3Y, pit3Y, salientPoint4Y, pit4Y,
					salientPoint5Y, pit5Y };
			g.drawPolygon(xPoints, yPoints, 10);

		}
	}

}
