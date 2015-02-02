package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo6_Gradient {
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
		new Graphics2DDemo6_Gradient().init();
	}

	/**
	 * 填充渐变色
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -293648272460279730L;

		public Panel2D() {
			setPreferredSize(new Dimension(400, 300));// 设置组件默认大小
		}

		// 重写组件绘制方法
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Rectangle2D rect = new Rectangle2D.Double(20, 20, 280, 180);
			GradientPaint paint = new GradientPaint(20, 20, Color.blue, 100,
					80, Color.red, true); // 创建渐变填充
			g2.setPaint(paint);// 设置渐变填充
			g2.fill(rect);
		}
	}

}
