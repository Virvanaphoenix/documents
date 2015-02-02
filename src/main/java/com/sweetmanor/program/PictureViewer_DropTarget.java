package com.sweetmanor.program;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * 没有添加对路径的判断，拖路径保存
 */
public class PictureViewer_DropTarget {
	JFrame jf = new JFrame("图片浏览器");
	private JDesktopPane desktop = new JDesktopPane(); // 定义一个虚拟桌面
	// 定义虚拟桌面大小
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT = 360;
	// 定义内部桌面大小
	private int width = DESKTOP_WIDTH / 2;
	private int height = DESKTOP_HEIGHT / 2;
	final int FRAME_DISTANCE = 30; // 定义两个窗口的显示位置差
	// 保存下一个内部窗口的坐标点
	private int nextFrameX;
	private int nextFrameY;

	public void init() {
		desktop.setPreferredSize(new Dimension(DESKTOP_WIDTH, DESKTOP_HEIGHT));
		new DropTarget(jf, DnDConstants.ACTION_COPY,
				new ImageDropTargetListener());// 将当前窗口创建成拖放目标
		jf.add(desktop);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new PictureViewer_DropTarget().init();
	}

	class ImageDropTargetListener extends DropTargetAdapter {
		@Override
		public void drop(DropTargetDropEvent dtde) {
			dtde.acceptDrop(DnDConstants.ACTION_COPY);// 接受复制操作
			Transferable transferable = dtde.getTransferable();// 获取拖放的内容
			DataFlavor[] flavors = transferable.getTransferDataFlavors();
			// 遍历拖放内容里的所有数据格式
			for (int i = 0; i < flavors.length; i++) {
				DataFlavor d = flavors[i];
				try {
					// 如果拖放内容的数据格式是文件列表
					if (d.equals(DataFlavor.javaFileListFlavor)) {
						List fileList = (List) transferable.getTransferData(d); // 取出文件列表
						// 显示每个文件
						for (Object f : fileList) {
							File file = (File) f;
							if (file.isFile()) {
								showImage(file, dtde);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				dtde.dropComplete(true);// 强制拖放操作接受，停止阻塞拖放目标，文件格式不对时不能一直阻塞
			}

		}

		// 显示图片文件
		private void showImage(File f, DropTargetDropEvent event)
				throws IOException {
			Image image = ImageIO.read(f);
			if (image == null) {
				event.dropComplete(true);// 强制拖放操作接受，停止阻塞拖放目标，文件格式不对时不能一直阻塞
				JOptionPane.showInternalMessageDialog(desktop, "系统不支持这种类型的文件");
				return;
			}
			ImageIcon icon = new ImageIcon(image);
			// 创建内部窗口显示该图片
			JInternalFrame iframe = new JInternalFrame(f.getName(), true, true,
					true, true);
			JLabel imageLabel = new JLabel(icon);
			iframe.add(new JScrollPane(imageLabel));
			desktop.add(iframe);
			iframe.reshape(nextFrameX, nextFrameY, width, height);// 设置内部窗体的位置和大小
			iframe.show();// 使该窗口可见，并尝试选中它
			// 计算下一个内部窗口的位置
			nextFrameX += FRAME_DISTANCE;
			nextFrameY += FRAME_DISTANCE;
			if (nextFrameX + width > desktop.getWidth()) {
				nextFrameX = 0;
			}
			if (nextFrameY + height > desktop.getHeight()) {
				nextFrameY = 0;
			}

		}

	}

}
