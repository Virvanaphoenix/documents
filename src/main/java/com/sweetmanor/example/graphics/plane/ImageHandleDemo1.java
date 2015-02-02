package com.sweetmanor.example.graphics.plane;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 图像效果示例：图像翻转、
 */
public class ImageHandleDemo1 {

	JFrame frame = new JFrame("图像效果演示");
	JSplitPane imagePanel = null;
	JPanel scrPanel = null;// 定义绘制源图像组件
	JPanel reversalPanel = null;// 定义翻转图像效果组件
	JPanel cutPanel = null;// 定义剪切图片效果组件
	Image image = null;// 图像

	int pressSrcX = 0, pressSrcY = 0;// 鼠标按下点在原图像上的X、Y坐标
	int pressX = 0, pressY = 0;// 鼠标按下点在屏幕上的X、Y坐标
	int releaseX = 0, releaseY = 0;// 鼠标释放点在屏幕上的X、Y坐标

	Robot robot = null; // 声明Robot对象
	BufferedImage cutImage = null;
	boolean flag = false; // 声明标记变量，为true时显示选择区域的矩形，否则不显示

	// 以下为翻转效果使用的参数
	int sx1, sy1, sx2, sy2;// 源矩形坐标值
	int dx1, dy1, dx2, dy2;// 目标矩形坐标值

	private void init() throws IOException {
		image = ImageIO.read(new File(CommonParam.resourcePath + "zoom.png"));// 加载图像
		scrPanel = new SrcPanel(image.getWidth(null), image.getHeight(null));// 初始化绘制源图像组件
		reversalPanel = new ReversalPanel(image.getWidth(null),
				image.getHeight(null));// 初始化翻转效果图像组件
		cutPanel = new CutPanel(image.getWidth(null), image.getHeight(null));// 初始化剪切效果组件

		imagePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				scrPanel, reversalPanel);// 创建左右分割面板
		frame.add(imagePanel);

		JPanel btPanel = new JPanel();// 创建按钮容器
		frame.add(btPanel, BorderLayout.SOUTH);
		addReversalEffect(btPanel);// 添加翻转效果演示
		addCutEffect();// 添加剪切效果演示

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addCutEffect() {
		scrPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {// 鼠标按下事件
				pressSrcX = e.getX();
				pressSrcY = e.getY();
				pressX = e.getXOnScreen();// 鼠标按下点在屏幕上的X坐标加1，即去除选择线
				pressY = e.getYOnScreen();// 鼠标按下点在屏幕上的Y坐标加1，即去除选择线
				flag = true; // 标记变量置为true
			}

			@Override
			public void mouseReleased(MouseEvent e) {// 鼠标释放事件
				releaseX = e.getXOnScreen() - 1;// 鼠标释放点在屏幕上的X坐标减1，即去除选择线
				releaseY = e.getYOnScreen() - 1;// 鼠标释放点在屏幕上的Y坐标减1，即去除选择线
				try {
					robot = new Robot();// 创建Robot对象
					if (releaseX - pressX > 0 && releaseY - pressY > 0) {
						Rectangle rect = new Rectangle(pressX, pressY, releaseX
								- pressX, releaseY - pressY);// 创建Rectangle对象
						cutImage = robot.createScreenCapture(rect);// 获得缓冲图像对象
						cutPanel.repaint(); // 调用CutImagePanel面板的paint()方法
					}
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				flag = false;// 为标记变量赋值为false
				imagePanel.setRightComponent(cutPanel);// 把剪切效果组件加入容器
			}
		});

		scrPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {// 鼠标拖动事件
				if (flag) {
					releaseX = e.getXOnScreen();// 获得鼠标释放点在屏幕上的X坐标
					releaseY = e.getYOnScreen();// 获得鼠标释放点在屏幕上的Y坐标
					cutPanel.repaint();
				}
			}
		});
	}

	public void addReversalEffect(JPanel btPanel) {
		// 初始化图像翻转各坐标值，初始目标矩形等于源矩形
		sx1 = 0;
		sy1 = 0;
		sx2 = image.getWidth(null);
		sy2 = image.getHeight(null);
		dx1 = sx1;
		dy1 = sy1;
		dx2 = sx2;
		dy2 = sy2;

		// 创建水平翻转按钮
		JButton btHorizontal = new JButton("水平翻转");
		// 创建监听器，交换目标矩形的X坐标实现水平翻转
		btHorizontal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = dx1;
				dx1 = dx2;
				dx2 = temp;
				imagePanel.setRightComponent(reversalPanel);// 把翻转效果组件加入容器
			}
		});
		btPanel.add(btHorizontal);
		// 创建垂直翻转按钮
		JButton btVertical = new JButton("垂直翻转");
		// 创建监听器，交换目标矩形的Y坐标实现垂直翻转
		btVertical.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = dy1;
				dy1 = dy2;
				dy2 = temp;
				imagePanel.setRightComponent(reversalPanel);// 把翻转效果组件加入容器
			}
		});
		btPanel.add(btVertical);
	}

	public static void main(String[] args) throws IOException {
		new ImageHandleDemo1().init();
	}

	/**
	 * 绘制原图像组件
	 */
	class SrcPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public SrcPanel(int width, int height) {
			setPreferredSize(new Dimension(width, height));// 以图片大小创建绘图对象O
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, 0, 0, null);
			g2.setColor(Color.RED);
			if (flag) {
				float[] arr = { 5.0f }; // 创建虚线模式的数组
				BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // 创建宽度是1的平头虚线笔画对象
				g2.setStroke(stroke);// 设置笔画对象
				g2.drawRect(pressSrcX, pressSrcY, releaseX - pressX, releaseY
						- pressY);// 绘制矩形选区
			}
		}

	}

	/**
	 * 绘制翻转图像组件
	 */
	class ReversalPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public ReversalPanel(int width, int height) {
			setPreferredSize(new Dimension(width, height));// 以图片大小创建绘图对象O
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清空
			g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);// 绘制图像
		}
	}

	/**
	 * 绘制剪切图像组件
	 */
	class CutPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public CutPanel(int width, int height) {
			setPreferredSize(new Dimension(width, height));// 以图片大小创建绘图对象O
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清空
			g.drawImage(cutImage, pressSrcX, pressSrcY, releaseX - pressX,
					releaseY - pressY, null);// 绘制图像
		}
	}

}
