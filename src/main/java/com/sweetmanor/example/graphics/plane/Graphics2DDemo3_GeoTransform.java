package com.sweetmanor.example.graphics.plane;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo3_GeoTransform {
	JFrame frame = new JFrame("绘图窗体");
	private int PANEL_WIDTH = 400;// 定义绘图区域大小
	private int PANEL_HEIGHT = 300;
	private int RECT_WIDTH = 100;// 定义长方形的大小
	private int RECT_HEIGHT = 80;
	JPanel panel = new Panel2D();

	/**
	 * 以下为几何变换效果参数
	 */
	private double scaleX = 1.0; // 定义缩放级别
	private double scaleY = 1.0;
	private double radian = 0.0; // 定义旋转的弧度
	private double shx = 0.0;// 定义剪裁转换连接参数
	private double shy = 0.0;

	public void init() {
		JPanel btScalePanel = new JPanel();// 定义缩放按钮容器
		// 定义放大按钮
		JButton zoomin = new JButton("放大");
		zoomin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scaleX *= 1.1;
				scaleY *= 1.1;
				panel.repaint();
			}
		});
		btScalePanel.add(zoomin);
		// 定义缩小按钮
		JButton zoomout = new JButton("缩小");
		zoomout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scaleX *= 0.9;
				scaleY *= 0.9;
				panel.repaint();
			}
		});
		btScalePanel.add(zoomout);
		// 定义还原按钮
		JButton restoreScale = new JButton("还原");
		restoreScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scaleX = 1.0;
				scaleY = 1.0;
				panel.repaint();
			}
		});
		btScalePanel.add(restoreScale);

		JPanel btRotatePanel = new JPanel();// 定义旋转按钮容器
		// 定义顺时针旋转按钮
		JButton clockwise = new JButton("顺时针");
		clockwise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radian += 0.1; // 旋转弧度值加0.1
				panel.repaint();
			}
		});
		btRotatePanel.add(clockwise);
		// 定义逆时针旋转按钮
		JButton anticlockwise = new JButton("逆时针");
		anticlockwise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radian -= 0.1;// 旋转弧度值减0.1
				panel.repaint();
			}
		});
		btRotatePanel.add(anticlockwise);
		// 定义还原按钮
		JButton restoreRotate = new JButton("还原");
		restoreRotate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radian = 0.0;// 旋转弧度值还原为0
				panel.repaint();
			}
		});
		btRotatePanel.add(restoreRotate);

		JPanel btShearPanel = new JPanel();
		// 定义上斜切按钮，单方向坐标变换可以实现倾斜效果
		JButton widdershins = new JButton("上斜切");
		widdershins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shx -= 0.1;
				shy -= 0.1;
				panel.repaint();
			}
		});
		btShearPanel.add(widdershins);

		// 定义下斜切按钮
		JButton deasil = new JButton("下斜切");
		deasil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shx += 0.1;
				shy += 0.1;
				panel.repaint();

			}
		});
		btShearPanel.add(deasil);

		// 定义还原按钮
		JButton restoreShear = new JButton("还原");
		restoreShear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shx = 0.0;
				shy = 0.0;
				panel.repaint();
			}
		});
		btShearPanel.add(restoreShear);

		// 组装容器组件
		Box box = Box.createVerticalBox();
		box.add(btScalePanel);
		box.add(btRotatePanel);
		box.add(btShearPanel);

		frame.add(panel);
		frame.add(box, BorderLayout.SOUTH);

		// 设置窗体显示属性
		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo3_GeoTransform().init();
	}

	/**
	 * 对图形进行缩放
	 * 
	 * @author Stars
	 * 
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -6856582600187745719L;

		public Panel2D() {
			// 设置组件显示大小
			setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			// 设置笔型
			BasicStroke stroke = new BasicStroke(10);
			g2.setStroke(stroke);
			// 清除之前的图形区域
			g2.clearRect(0, 0, getWidth(), getHeight());
			// 设置一个居中的长方形
			Rectangle2D.Float rect = new Rectangle2D.Float(
					(PANEL_WIDTH - RECT_WIDTH) / 2,
					(PANEL_HEIGHT - RECT_HEIGHT) / 2, RECT_WIDTH, RECT_HEIGHT);

			// 以下为几何变换效果参数
			g2.scale(scaleX, scaleY);// 进行缩放
			g2.rotate(radian);// 进行旋转
			g2.shear(shx, shy); // 进行剪裁转换连接

			g2.draw(rect);
		}
	}

}
