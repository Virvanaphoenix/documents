package com.sweetmanor.example.gui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

/**
 * 格式化文本框使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JFormattedTextFieldDemo {
	JFrame mainWin = new JFrame("测试格式化文本框");
	JButton okButton = new JButton("确定");

	JPanel mainPanel = new JPanel();
	JFormattedTextField[] fields = new JFormattedTextField[6];
	String[] behaviorLabels = new String[] { "COMMIT", "COMMIT_OR_REVERT",
			"PERSIST", "REVERT" };

	int[] behaviors = new int[] { JFormattedTextField.COMMIT,
			JFormattedTextField.COMMIT_OR_REVERT, JFormattedTextField.PERSIST,
			JFormattedTextField.REVERT };

	ButtonGroup bg = new ButtonGroup();

	public void init() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		mainPanel.setLayout(new GridLayout(0, 3));
		mainWin.add(mainPanel);

		// ----------------------使用NumberFormat的integerInstance创建一个JFormattedTextField对象
		fields[0] = new JFormattedTextField(NumberFormat.getIntegerInstance());
		fields[0].setValue(100);
		addRow("整数格式文本框：", fields[0]);

		// ---------------------
		fields[1] = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		fields[1].setValue(100.0);
		addRow("货币格式文本框", fields[1]);

		// ----------------------
		fields[2] = new JFormattedTextField(DateFormat.getDateInstance());
		fields[2].setValue(new Date());
		addRow("默认的日期格式器", fields[2]);

		// --------------------------
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		format.setLenient(false);
		fields[3] = new JFormattedTextField(format);
		fields[3].setValue(new Date());
		addRow("SHORT类型的日期格式器（语法严格）：", fields[3]);

		// -----------------------------
		try {
			DefaultFormatter formatter = new DefaultFormatter();
			formatter.setOverwriteMode(false);
			fields[4] = new JFormattedTextField(formatter);
			fields[4].setValue(new URL("http://www.crazyit.org"));
			addRow("URL:", fields[4]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// ------------------------
		try {
			MaskFormatter formatter = new MaskFormatter("020-########");
			formatter.setPlaceholderCharacter('□');
			fields[5] = new JFormattedTextField(formatter);
			fields[5].setValue("020-28309378");
			addRow("电话号码", fields[5]);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JPanel focusLostPanel = new JPanel();
		for (int i = 0; i < behaviorLabels.length; i++) {
			final int index = i;
			final JRadioButton radio = new JRadioButton(behaviorLabels[i]);
			if (i == 1) {
				radio.setSelected(true);
			}
			focusLostPanel.add(radio);
			bg.add(radio);
			radio.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (radio.isSelected()) {
						for (int j = 0; j < fields.length; j++) {
							fields[j].setFocusLostBehavior(behaviors[index]);
						}
					}
				}
			});

		}
		focusLostPanel.setBorder(new TitledBorder(new EtchedBorder(),
				"请选择焦点失去后的行为"));
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(focusLostPanel, BorderLayout.NORTH);
		p.add(buttonPanel, BorderLayout.SOUTH);

		mainWin.add(p, BorderLayout.SOUTH);
		mainWin.pack();
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setVisible(true);
	}

	private void addRow(String labelText, final JFormattedTextField field) {
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(field);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object value = field.getValue();
				valueLabel.setText(value.toString());
			}
		});
	}

	public static void main(String[] args) {
		new JFormattedTextFieldDemo().init();
	}

}
