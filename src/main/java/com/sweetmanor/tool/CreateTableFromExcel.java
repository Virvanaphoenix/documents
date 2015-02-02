package com.sweetmanor.tool;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sweetmanor.util.DateUtil;
import com.sweetmanor.util.FrameUtil;
import com.sweetmanor.util.JDBCUtil;
import com.sweetmanor.util.office.ExcelUtil;

public class CreateTableFromExcel {
	private JDBCUtil jdbcUtil;
	private JFileChooser fileChooser = new JFileChooser(".");
	private List<TableColumn> columns;
	DefaultTableModel model;
	JTable table;

	public CreateTableFromExcel() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("根据EXCEL文件创建数据库表");
				Box container = Box.createVerticalBox();
				frame.add(container);

				JPanel dbPanel = createDBPanel();
				container.add(dbPanel);

				JPanel excelPanel = createLoadExcelPanel();
				container.add(excelPanel);

				JPanel tablePanel = createTablePanel();
				container.add(tablePanel);

				frame.pack();
				FrameUtil.center(frame);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	private JPanel createDBPanel() {
		JPanel dbPanel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);// LineBorder：单线边框
		border = BorderFactory.createTitledBorder(border, "数据库连接参数",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("宋体", Font.BOLD,
						12), Color.BLACK);
		dbPanel.setBorder(border);// 为组件设置边框

		dbPanel.add(new JLabel("IP："));
		final JTextField ipField = new JTextField(10);
		ipField.setText("127.0.0.1");
		dbPanel.add(ipField);
		dbPanel.add(Box.createHorizontalStrut(15));

		dbPanel.add(new JLabel("端口："));
		final JTextField portField = new JTextField(10);
		portField.setText("1521");
		dbPanel.add(portField);
		dbPanel.add(Box.createHorizontalStrut(15));

		dbPanel.add(new JLabel("数据库名称："));
		final JTextField orclField = new JTextField(10);
		orclField.setText("orcl");
		dbPanel.add(orclField);
		dbPanel.add(Box.createHorizontalStrut(15));

		dbPanel.add(new JLabel("用户名："));
		final JTextField userField = new JTextField(10);
		dbPanel.add(userField);
		dbPanel.add(Box.createHorizontalStrut(15));

		dbPanel.add(new JLabel("密码："));
		final JTextField passField = new JTextField(10);
		dbPanel.add(passField);
		dbPanel.add(Box.createHorizontalStrut(15));

		JButton btConn = new JButton("连接数据库");
		dbPanel.add(btConn);
		btConn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@" + ipField.getText().trim()
						+ ":" + portField.getText().trim() + ":"
						+ orclField.getText().trim();
				String user = userField.getText().trim();
				String pass = passField.getText().trim();

				jdbcUtil = new JDBCUtil(driver, url, user, pass);
				jdbcUtil.connection();
			}
		});
		return dbPanel;
	}

	private JPanel createLoadExcelPanel() {
		JPanel excelPanel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);// LineBorder：单线边框
		border = BorderFactory.createTitledBorder(border, "EXCEL文件加载",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("宋体", Font.BOLD,
						12), Color.BLACK);
		excelPanel.setBorder(border);// 为组件设置边框

		excelPanel.add(new JLabel("文件路径："));
		final JTextField pathField = new JTextField(30);
		excelPanel.add(pathField);
		excelPanel.add(Box.createHorizontalStrut(15));

		JButton btBrowser = new JButton("浏览");
		btBrowser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showDialog(null, "选择EXCEL文件");
				if (result == JFileChooser.APPROVE_OPTION)
					pathField.setText(fileChooser.getSelectedFile().getPath());
			}
		});
		excelPanel.add(btBrowser);
		excelPanel.add(Box.createHorizontalStrut(15));

		excelPanel.add(new JLabel("标题行号："));
		final JTextField titleRowField = new JTextField(3);
		titleRowField.setText("1");
		excelPanel.add(titleRowField);
		excelPanel.add(Box.createHorizontalStrut(15));

		JButton btLoadTitle = new JButton("获取标题");
		btLoadTitle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String filePath = pathField.getText().trim();
					List<List<String>> content = ExcelUtil.read(filePath, 0);
					List<String> titles = content.get(0);
					columns = new ArrayList<TableColumn>();
					int index = 1;
					for (String cell : titles) {
						TableColumn column = new TableColumn("filed" + index,
								"VARCHAR2", 100, cell);
						columns.add(column);
						index++;
					}
					updateView();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		excelPanel.add(btLoadTitle);
		excelPanel.add(Box.createHorizontalStrut(15));

		return excelPanel;
	}

	private JPanel createTablePanel() {
		JPanel tablePanel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);// LineBorder：单线边框
		border = BorderFactory.createTitledBorder(border, "数据库建表模版",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("宋体", Font.BOLD,
						12), Color.BLACK);
		tablePanel.setBorder(border);// 为组件设置边框

		Box left = Box.createVerticalBox();

		model = new DefaultTableModel();

		model.addColumn("字段名称");
		model.addColumn("字段类型");
		model.addColumn("字段长度");
		model.addColumn("字段说明");

		table = new JTable(model);
		left.add(new JScrollPane(table));

		JPanel btPanel = new JPanel();
		left.add(btPanel);
		tablePanel.add(left);
		final JTextArea taView = new JTextArea(20, 20);
		tablePanel.add(taView);

		JButton btView = new JButton("预览执行语句");
		btView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = createSql();
					taView.setText(sql);
				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		btPanel.add(btView);
		JButton btExecute = new JButton("执行");
		btExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = createSql();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btPanel.add(btExecute);

		return tablePanel;
	}

	private void updateView() {
		if (columns != null) {
			for (TableColumn column : columns) {
				String[] newCells = new String[4];
				newCells[0] = column.getColumnName();
				newCells[1] = column.getColumnType();
				newCells[2] = column.getLenght() + "";
				newCells[3] = column.getComments();
				model.addRow(newCells);
			}
		}
	}

	private String createSql() throws Exception {
		String sql = "";
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		String tableName = "TEM_" + DateUtil.format(date, "yyyyMMddHHmmss");
		String s = "create table " + tableName + "\n";
		sb.append(s);

		s = "(\n";
		sb.append(s);

		for (int i = 0; i < model.getRowCount(); i++) {
			String name = model.getValueAt(i, 0).toString();
			String type = model.getValueAt(i, 1).toString();
			String len = model.getValueAt(i, 2).toString();
			if (!"NUMBER".equalsIgnoreCase(type)
					&& !"DATE".equalsIgnoreCase(type)
					&& !"VARCHAR2".equalsIgnoreCase(type))
				throw new Exception("字段类型不支持！");
			s = name + " " + type;
			if ("VARCHAR2".equalsIgnoreCase(type)) {
				s += "(" + len + ")";
			}
			if (i != model.getRowCount() - 1)
				s += ",\n";
			else
				s += "\n";
			sb.append(s);
		}
		s = ")\n";
		sb.append(s);
		sql = sb.toString();
		jdbcUtil.execute(sql);

		for (int i = 0; i < model.getRowCount(); i++) {
			String name = model.getValueAt(i, 0).toString();
			String comments = model.getValueAt(i, 3).toString();
			s = "comment on column " + tableName + "." + name + " is '"
					+ comments + "'\n";
			jdbcUtil.execute(s);
		}

		JOptionPane.showMessageDialog(null, "表创建成功，表名：" + tableName);
		return sb.toString();
	}

	public static void main(String[] args) {
		new CreateTableFromExcel();
	}

	class TableColumn {
		private String columnName;
		private String columnType;
		private int lenght = 100;
		private String comments;

		public TableColumn(String columnName, String columnType, int lenght,
				String comments) {
			this.columnName = columnName;
			this.columnType = columnType;
			this.lenght = lenght;
			this.comments = comments;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnType() {
			return columnType;
		}

		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}

		public int getLenght() {
			return lenght;
		}

		public void setLenght(int lenght) {
			this.lenght = lenght;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}
	}

}
