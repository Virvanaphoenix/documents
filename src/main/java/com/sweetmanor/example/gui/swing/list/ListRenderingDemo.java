package com.sweetmanor.example.gui.swing.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

public class ListRenderingDemo {
	JFrame mainWin = new JFrame("好友列表");
	String[] friends = new String[] { "李清照", "苏格拉底", "李白", "弄玉", "虎头" };
	JList friendsList = new JList(friends);

	public void init() {
		friendsList.setCellRenderer(new ImageCellRenderer());
		mainWin.add(new JScrollPane(friendsList));
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);

	}

	public static void main(String[] args) {
		new ListRenderingDemo().init();
	}

}

class ImageCellRenderer extends JPanel implements ListCellRenderer {
	ImageIcon icon;
	String name;
	Color background;
	Color foreground;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		icon = new ImageIcon("ico/" + value + ".gif");
		name = value.toString();
		background = isSelected ? list.getSelectionBackground() : list
				.getBackground();
		foreground = isSelected ? list.getSelectionForeground() : list
				.getForeground();
		return this;
	}

	public void paintComponent(Graphics g) {
		int imageWidth = icon.getImage().getWidth(null);
		int imageHeight = icon.getImage().getHeight(null);
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foreground);
		g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2, 10, null);
		g.setFont(new Font("sansSerif", Font.BOLD, 18));
		g.drawString(name, getWidth() / 2 - name.length() * 10,
				imageHeight + 30);

	}

	public Dimension getPreferredSize() {
		return new Dimension(60, 80);
	}
}
