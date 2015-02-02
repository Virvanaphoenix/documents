package com.sweetmanor.example.graphics.plane;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 本示例描述平面图像类Graphics的一般处理流程
 * 
 * 将一个类同时写成Applet和Application两种类型
 */
public class G2Demo1_CommonFlow extends JApplet {

	private static final long serialVersionUID = -676339015384096346L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JApplet applet = new G2Demo1_CommonFlow(); // 创建Graphics2DDemo1对象
		applet.init(); // 调用方法进行初始化
		frame.getContentPane().add(applet); // 将Graphics2DDemo1对象加入窗体容器

		frame.setTitle("Java 2D Demo");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 重写初始化方法
	@Override
	public void init() {
		JPanel panel = new Panel2D();
		getContentPane().add(panel);
	}

	// 定义Panel2D内部类
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -7919079679228232807L;

		// 无参构造函数
		public Panel2D() {
			setPreferredSize(new Dimension(500, 400)); // 设置大小
			setBackground(Color.white); // 设置背景色
		}

		// 重写绘制组件方法
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);// 调用父类的重绘方法
			Graphics2D g2 = (Graphics2D) g;// 将Graphics类型强制转换成Graphics2D类型
			Shape ellipse = new Ellipse2D.Double(150, 100, 200, 200);// 创建一个椭圆形状
			GradientPaint paint = new GradientPaint(100, 100, Color.white, 400,
					400, Color.gray);// 创建一个线性渐变模式
			g2.setPaint(paint);
			g2.fill(ellipse);// 用线性渐变填充椭圆
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.4f);// 创建合成规则
			g2.setComposite(ac);
			g2.setColor(Color.blue);
			Font font = new Font("Serif", Font.BOLD, 120);
			g2.setFont(font);
			g2.drawString("Java", 120, 200);// 以指定字体，颜色和合成规则绘制文字
			FontRenderContext frc = g2.getFontRenderContext();// 创建文本信息测量容器
			GlyphVector gv = font.createGlyphVector(frc, "2D");// 以指定字体生成文本的几何信息集
			Shape glyph = gv.getOutline(150, 300);// 获取文本信息集在指定坐标显示时的外线轮廓
			g2.rotate(Math.PI / 6, 200, 300);// 旋转坐标
			g2.fill(glyph);// 填充指定图形
		}
	}

}
