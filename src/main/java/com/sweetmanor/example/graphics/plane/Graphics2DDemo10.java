package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo10 {

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
		new Graphics2DDemo10().init();
	}

	class Panel2D extends JPanel {
		private static final long serialVersionUID = 2053014403269962894L;

		public Panel2D() {
			setPreferredSize(new Dimension(400, 300));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(panel.getWidth() / 2, panel.getHeight() / 2);
			// 绘制圆心
			Ellipse2D ellipse = new Ellipse2D.Double(-5, -5, 10, 10);
			g2.setColor(Color.red);
			g2.fill(ellipse);
			// 绘制绿色花瓣
			ellipse = new Ellipse2D.Double(50, 0, 90, 20);
			g2.setColor(Color.red);
			for (int i = 0; i < 8; i++) {
				g2.rotate(Math.PI / 4);
				g2.fill(ellipse);
			}
			// 绘制蓝色花瓣
			ellipse = new Ellipse2D.Double(40, 0, 80, 15);
			g2.setColor(Color.blue);
			for (int i = 0; i < 12; i++) {
				g2.rotate(Math.PI / 6);
				g2.fill(ellipse);
			}
			// 绘制蓝色花瓣
			ellipse = new Ellipse2D.Double(30, 0, 70, 10);
			g2.setColor(Color.yellow);
			for (int i = 0; i < 16; i++) {
				g2.rotate(Math.PI / 8);
				g2.fill(ellipse);
			}
		}
	}

}
