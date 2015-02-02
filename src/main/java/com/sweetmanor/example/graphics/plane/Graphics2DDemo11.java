package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo11 {

	JFrame frame = new JFrame("绘图窗体");
	JPanel panel = new Panel2D();

	private void init() {
		frame.add(panel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo11().init();
	}

	/**
	 * 绘制正八边形
	 */
	class Panel2D extends JPanel {

		private static final long serialVersionUID = 2071044703439114237L;

		public Panel2D() {
			setPreferredSize(new Dimension(300, 300));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			// BasicStroke stroke = new BasicStroke(3);// 设置线型属性
			// g2.setStroke(stroke);

			double x1 = 0.0;
			double y1 = 0.0;
			double x2 = 1;
			double y2 = 0.0;
			double x3 = x2 + Math.sin(Math.PI / 4);
			double y3 = Math.cos(Math.PI / 4);
			double x4 = x3;
			double y4 = y3 + 1;
			double x5 = x2;
			double y5 = y4 + Math.sin(Math.PI / 4);
			double x6 = x1;
			double y6 = y5;
			double x7 = x6 - Math.sin(Math.PI / 4);
			double y7 = y4;
			double x8 = x7;
			double y8 = y3;

			Path2D path = new Path2D.Double();// 按奇偶规则创建一般路径对象
			// 绘制正八边形
			path.moveTo(x1, y1);
			path.lineTo(x2, y2);
			path.lineTo(x3, y3);
			path.lineTo(x4, y4);
			path.lineTo(x5, y5);
			path.lineTo(x6, y6);
			path.lineTo(x7, y7);
			path.lineTo(x8, y8);
			path.closePath();

			AffineTransform tr = new AffineTransform(); // 定义仿射变换对象
			tr.scale(100, 100); // 放大100倍
			g2.translate(100, 30); // 坐标系平移

			path = (Path2D) tr.createTransformedShape(path); // 对路径对象应用仿射变换规则
			g2.setColor(Color.red);
			Area area = new Area(path);// 将路径转换为区域对象，将只绘制外边框
			g2.draw(area); // 绘制路径对象

			// 绘制第1个坐标点
			Shape ellipse = new Ellipse2D.Double(x1 - 0.03, y1 - 0.03, 0.06,
					0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.red);
			g2.fill(ellipse);
			// 绘制第2个坐标点
			ellipse = new Ellipse2D.Double(x2 - 0.03, y2 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.orange);
			g2.fill(ellipse);
			// 绘制第3个坐标点
			ellipse = new Ellipse2D.Double(x3 - 0.03, y3 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.yellow);
			g2.fill(ellipse);
			// 绘制第4个坐标点
			ellipse = new Ellipse2D.Double(x4 - 0.03, y4 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.green);
			g2.fill(ellipse);
			// 绘制第5个坐标点
			ellipse = new Ellipse2D.Double(x5 - 0.03, y5 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.cyan);
			g2.fill(ellipse);
			// 绘制第6个坐标点
			ellipse = new Ellipse2D.Double(x6 - 0.03, y6 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.blue);
			g2.fill(ellipse);
			// 绘制第7个坐标点
			ellipse = new Ellipse2D.Double(x7 - 0.03, y7 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.pink);
			g2.fill(ellipse);
			// 绘制第8个坐标点
			ellipse = new Ellipse2D.Double(x8 - 0.03, y8 - 0.03, 0.06, 0.06);
			ellipse = tr.createTransformedShape(ellipse);
			g2.setColor(Color.black);
			g2.fill(ellipse);
		}
	}

}
