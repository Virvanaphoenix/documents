package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo9 {

	JFrame frame = new JFrame("绘图窗体");
	JPanel panel = new Panel2D();
	Image image = null; // 声明图像对象

	private void init() {
		frame.add(panel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo9().init();
	}

	class Panel2D extends JPanel {
		private static final long serialVersionUID = 2053014403269962894L;

		public Panel2D() {
			setPreferredSize(new Dimension(650, 250));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			// BasicStroke stroke = new BasicStroke(3);// 设置线型属性
			// g2.setStroke(stroke);

			// 设置点坐标
			double x1 = 1.0;
			double y1 = 0.0;
			double x2 = Math.cos(2 * Math.PI / 5.0);
			double y2 = Math.sin(2 * Math.PI / 5.0);
			double x3 = Math.cos(4 * Math.PI / 5.0);
			double y3 = Math.sin(4 * Math.PI / 5.0);
			double x4 = Math.cos(6 * Math.PI / 5.0);
			double y4 = Math.sin(6 * Math.PI / 5.0);
			double x5 = Math.cos(8 * Math.PI / 5.0);
			double y5 = Math.sin(8 * Math.PI / 5.0);

			Path2D path = new Path2D.Double();// 按奇偶规则创建一般路径对象
			// 绘制五角星
			path.moveTo(x1, y1);
			path.lineTo(x3, y3);
			path.lineTo(x5, y5);
			path.lineTo(x2, y2);
			path.lineTo(x4, y4);
			path.closePath();

			AffineTransform tr = new AffineTransform(); // 定义仿射变换对象
			tr.scale(100, 100); // 放大100倍
			g2.translate(120, 120); // 坐标系平移
			g2.rotate(-Math.PI / 2); // 坐标系逆时针旋转90度

			path = (Path2D) tr.createTransformedShape(path); // 对路径对象应用仿射变换规则
			g2.setColor(Color.red);
			Area area = new Area(path);// 将路径转换为区域对象，将只绘制外边框
			g2.draw(area); // 绘制路径对象

			// // 绘制第1个坐标点
			// Shape ellipse = new Ellipse2D.Double(x1 - 0.03, y1 - 0.03, 0.06,
			// 0.06);
			// ellipse = tr.createTransformedShape(ellipse);
			// g2.setColor(Color.red);
			// g2.fill(ellipse);
			// // 绘制第2个坐标点
			// ellipse = new Ellipse2D.Double(x2 - 0.03, y2 - 0.03, 0.06, 0.06);
			// ellipse = tr.createTransformedShape(ellipse);
			// g2.setColor(Color.orange);
			// g2.fill(ellipse);
			// // 绘制第3个坐标点
			// ellipse = new Ellipse2D.Double(x3 - 0.03, y3 - 0.03, 0.06, 0.06);
			// ellipse = tr.createTransformedShape(ellipse);
			// g2.setColor(Color.yellow);
			// g2.fill(ellipse);
			// // 绘制第4个坐标点
			// ellipse = new Ellipse2D.Double(x4 - 0.03, y4 - 0.03, 0.06, 0.06);
			// ellipse = tr.createTransformedShape(ellipse);
			// g2.setColor(Color.green);
			// g2.fill(ellipse);
			// // 绘制第5个坐标点
			// ellipse = new Ellipse2D.Double(x5 - 0.03, y5 - 0.03, 0.06, 0.06);
			// ellipse = tr.createTransformedShape(ellipse);
			// g2.setColor(Color.blue);
			// g2.fill(ellipse);

			g2.setColor(Color.black);
			// 设定为奇偶缠绕规则
			path.setWindingRule(Path2D.WIND_EVEN_ODD);
			g2.translate(0, 200); // 由于之前的坐标系旋转，所以此处的横向移动要改变y坐标
			g2.fill(path);

			// 设定为非0缠绕规则，一般非0缠绕规则的内部要比奇偶缠绕的内部多
			path.setWindingRule(Path2D.WIND_NON_ZERO);
			g2.translate(0, 200);
			g2.fill(path);

		}
	}

}
