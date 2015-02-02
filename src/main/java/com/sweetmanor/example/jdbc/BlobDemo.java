package com.sweetmanor.example.jdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;

public class BlobDemo {
	JFrame jf = new JFrame("图片管理程序");
	private static Connection conn;
	private static PreparedStatement insert;
	private static PreparedStatement query;
	private static PreparedStatement queryAll;
	// 定义一个DefaultListModel对象
	private DefaultListModel<ImageHolder> imageModel = new DefaultListModel<ImageHolder>();
	private JList<ImageHolder> imageList = new JList<ImageHolder>(imageModel);
	private JTextField filePath = new JTextField(26);
	private JButton browserBn = new JButton("...");
	private JButton uploadBn = new JButton("上传");
	private JLabel imageLabel = new JLabel();
	// 以当前路径创建文件选择器
	JFileChooser chooser = new JFileChooser(".");
	// 创建文件过滤器
	ExtensionFileFilter filter = new ExtensionFileFilter();

	static {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("mysql.ini"));
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("pass");
			Class.forName(driver);
			// 获取数据库连接
			conn = DriverManager.getConnection(url, user, password);
			// 创建执行插入的PrepareStetement对象
			// 该对象执行插入后可以返回自动生成的主键
			insert = conn.prepareStatement(
					"insert into img_table values(null,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			// 创建两个PreparedStatement对象，用于查询指定图片，查询所有图片
			query = conn
					.prepareStatement("select img_data from img_table where img_id=?");
			queryAll = conn
					.prepareStatement("select img_id,img_name from img_table");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws SQLException {
		// 初始化文件选择器
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
		chooser.addChoosableFileFilter(filter);
		// 禁止“文件类型”下拉列表中显示“所有文件”选项
		chooser.setAcceptAllFileFilterUsed(false);
		fillListModel();
		filePath.setEditable(false);
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel jp = new JPanel();
		jp.add(filePath);
		jp.add(browserBn);
		browserBn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showDialog(jf, "浏览图片文件上传");
				if (result == JFileChooser.APPROVE_OPTION) {
					filePath.setText(chooser.getSelectedFile().getPath());
				}
			}
		});
		jp.add(uploadBn);
		uploadBn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePath.getText().trim().length() > 0) {
					upload(filePath.getText());
					filePath.setText("");
				}
			}
		});
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
		left.add(jp, BorderLayout.SOUTH);
		jf.add(left);
		imageList.setFixedCellWidth(160);
		jf.add(new JScrollPane(imageList), BorderLayout.EAST);
		imageList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					ImageHolder cur = (ImageHolder) imageList
							.getSelectedValue();
					try {
						showImage(cur.getId());
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}
		});
		jf.setSize(620, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	// 查找img_table表填充ListModel
	public void fillListModel() throws SQLException {
		try {
			ResultSet rs = queryAll.executeQuery();
			// 先清除所有元素
			imageModel.clear();
			// 把查询的全部记录添加到ListModel中
			while (rs.next()) {
				imageModel.addElement(new ImageHolder(rs.getInt(1), rs
						.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 将指定图片放入数据库
	public void upload(String fileName) {
		String imageName = fileName.substring(fileName.lastIndexOf('\\') + 1,
				fileName.lastIndexOf('.'));
		File f = new File(fileName);
		try {
			InputStream is = new FileInputStream(f);
			insert.setString(1, imageName);
			insert.setBinaryStream(2, is, (int) f.length());
			int affect = insert.executeUpdate();
			if (affect == 1) {
				fillListModel();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showImage(int id) throws SQLException {
		query.setInt(1, id);
		try {
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Blob imgBlob = rs.getBlob(1);
				ImageIcon icon = new ImageIcon(imgBlob.getBytes(1L,
						(int) imgBlob.length()));
				imageLabel.setIcon(icon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		new BlobDemo().init();
	}

}

// 创建FileFilter的子类，用以实现文件过滤功能
class ExtensionFileFilter extends FileFilter {
	private String description = "";
	private ArrayList<String> extensions = new ArrayList<String>();

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// 自定义方法，用于添加文件扩展名
	public void addExtension(String extension) {
		if (!extension.endsWith(".")) {
			extension = "." + extension;
			extensions.add(extension.toLowerCase());
		}
	}

	@Override
	public boolean accept(File f) {
		// 如果该文件是路径，则接受该文件
		if (f.isDirectory())
			return true;
		// 将文件名转换为小写后用于比较
		String name = f.getName().toLowerCase();
		for (String extension : extensions) {
			if (name.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
}

// 创建一个ImageHolder类，用于封装图片名、图片id
class ImageHolder {
	private int id;
	private String name;

	@Override
	public String toString() {
		return name;
	}

	public ImageHolder() {
		super();
	}

	public ImageHolder(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}