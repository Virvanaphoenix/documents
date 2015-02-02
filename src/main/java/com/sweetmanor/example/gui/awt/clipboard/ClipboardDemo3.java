package com.sweetmanor.example.gui.awt.clipboard;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.sweetmanor.common.Person;
import com.sweetmanor.util.FrameUtil;

/**
 * 系统剪贴板使用示例：通过剪贴板传递Java对象
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ClipboardDemo3 {
	private JFrame frame = new JFrame("剪贴板传递Java对象");

	public void init() {
		final Clipboard clipboard = Toolkit.getDefaultToolkit()
				.getSystemClipboard(); // 创建系统剪贴板
		// Clipboard clipboard = new Clipboard("cb"); // 创建本地剪贴板

		Panel top = new Panel();
		frame.add(top, BorderLayout.NORTH);

		top.add(new Label("姓名"));
		final TextField name = new TextField(15);
		top.add(name);
		top.add(new Label("年龄"));
		final TextField age = new TextField(15);
		top.add(age);

		final TextArea ta = new TextArea(3, 20);
		frame.add(ta);

		Panel btPanel = new Panel();
		frame.add(btPanel, BorderLayout.SOUTH);

		Button btCopy = new Button("复制");
		btCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Person person = new Person(name.getText(), Integer.parseInt(age
						.getText()));
				SerialSelection serialPerson = new SerialSelection(person);// 把Person封装成SerialSelection对象
				clipboard.setContents(serialPerson, null);// 把SerialSelection对象放入剪贴板
			}
		});
		btPanel.add(btCopy);

		Button btPaste = new Button("粘贴");
		btPaste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DataFlavor personFlavor = new DataFlavor(
							DataFlavor.javaSerializedObjectMimeType + ";class="
									+ Person.class.getName());// Person类型的对象格式
					if (clipboard.isDataFlavorAvailable(personFlavor)) {
						Person person = (Person) clipboard
								.getData(personFlavor);
						ta.setText(person.toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btPanel.add(btPaste);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ClipboardDemo3().init();
	}

}
