package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.sweetmanor.util.FrameUtil;

/**
 * 演示JColorChooser颜色选择对话框的使用。JColorChooser有两种调用方式，一般使用JColorChooser.showDialog即可
 */
public class HandDrawDemo2_JColorChooser {
	private JFrame frame = new JFrame("简单手绘程序");
	private DrawCanvas drawArea = new DrawCanvas();// 创建自定义画图组件
	private final int AREA_WIDTH = 500;// 定义画图区域大小
	private final int AREA_HEIGHT = 400;
	private int preX = -1;// 保存拖动前鼠标坐标
	private int preY = -1;
	JPopupMenu popup = new JPopupMenu();// 创建右键菜单选择画笔颜色
	JMenuItem chooseColor = new JMenuItem("选择颜色");
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_RGB);// 创建BufferedImage对象
	Graphics g = image.getGraphics();
	private Color foreColor = new Color(255, 0, 0);// 保存画笔颜色

	public void init() {
		chooseColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 使用默认颜色对话框选择颜色
				// foreColor = JColorChooser.showDialog(frame,
				// "选择画笔颜色",foreColor);
				// 创建自定义颜色对话框选择颜色
				final JColorChooser colorPane = new JColorChooser(foreColor);
				JDialog dialog = JColorChooser.createDialog(frame, "选择画笔颜色",
						false, colorPane, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								foreColor = colorPane.getColor();// 点击“确定”按钮时获取选择的颜色
							}
						}, null);
				dialog.setVisible(true);// 弹出颜色对话框
			}
		});
		popup.add(chooseColor);// 组合右键菜单
		drawArea.setComponentPopupMenu(popup);

		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);// 清空绘图区域背景
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));// 设置绘图区域大小
		// 监听绘图区域按下并拖动鼠标事件
		drawArea.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (preX > 0 && preY > 0) {
					g.setColor(foreColor);// 设置画笔颜色
					g.drawLine(preX, preY, e.getX(), e.getY());// 绘制线条
				}
				preX = e.getX();// 保存当前坐标
				preY = e.getY();
				drawArea.repaint();// 重绘组件
			}
		});
		// 监听鼠标释放事件
		drawArea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				preX = -1;// 清除坐标值
				preY = -1;
			}
		});
		frame.add(drawArea);// 将绘图区域添加到窗体

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new HandDrawDemo2_JColorChooser().init();
	}

	class DrawCanvas extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
