package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo4 {
	JFrame frame = new JFrame("绘图窗体");
	JPanel panel = new Panel2D();

	public void init() {
		frame.add(panel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo4().init();
	}

	/**
	 * 图形绘制的基本步骤如下： 1、构建2D图形对象； 2、对构建的对象进行几何变换； 3、应用颜色和其他绘制属性； 4、在图形设备上绘制场景。
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = 7396208036683555691L;

		public Panel2D() {
			setPreferredSize(new Dimension(600, 400));// 设置组件默认大小
		}

		// 重写组件绘制方法
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.blue);// 设置颜色
			Ellipse2D e = new Ellipse2D.Double(-100, -50, 200, 100); // 构建图形
			AffineTransform tr = new AffineTransform();// 定义仿射变换
			tr.rotate(Math.PI / 6.0);// 应用旋转
			Shape shape = tr.createTransformedShape(e);// 基于仿射变换创建图形
			g2.translate(300, 200);// 进行坐标系平移
			g2.scale(2, 2);// 进行缩放
			g2.draw(shape);// 绘制图形
			g2.drawString("Hello 2D", 0, 0);// 绘制文本
		}
	}

}
