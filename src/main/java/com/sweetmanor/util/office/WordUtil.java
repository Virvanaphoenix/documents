package com.sweetmanor.util.office;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordUtil {
	// word运行程序对象
	private ActiveXComponent MsWordApp = null;
	// 所有word文档集合
	private Dispatch documents = null;
	// word文档
	private Dispatch doc = null;
	// 选定的范围或插入点
	private Dispatch selection = null;

	public WordUtil() {
		// 初始化word程序和文档集对象
		if (MsWordApp == null) {
			MsWordApp = new ActiveXComponent("Word.Application");
		}
		if (documents == null)
			documents = MsWordApp.getProperty("Documents").toDispatch();
	}

	/**
	 * 创建一个新的word文档
	 */
	public void createDocument() {
		// 初始化一个word文档和插入点坐标
		doc = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
	}

	/**
	 * 把文字写入word文档中
	 * 
	 * @param content
	 *            要写入的内容
	 */
	public void writeToDocument(String content) {
		Dispatch.put(selection, "Text", content);
	}

	// 段落的处理,插入格式化的文本
	public void insertFormatStr(String text) {
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得word文件的内容
		Dispatch.call(wordContent, "InsertAfter", text);// 插入一个段落到最后

		Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
				.toDispatch(); // 所有段落
		int paragraphCount = Dispatch.get(paragraphs, "Count")
				.changeType(Variant.VariantInt).getInt();// 一共的段落数

		// 找到刚输入的段落，设置格式
		Dispatch lastParagraph = Dispatch.call(paragraphs, "Item",
				new Variant(paragraphCount)).toDispatch(); // 最后一段（也就是刚插入的）
		// Range 对象表示文档中的一个连续范围，由一个起始字符位置和一个终止字符位置定义
		Dispatch lastParagraphRange = Dispatch.get(lastParagraph, "Range")
				.toDispatch();

		Dispatch font = Dispatch.get(lastParagraphRange, "Font").toDispatch();
		Dispatch.put(font, "Bold", new Variant(true)); // 设置为黑体
		Dispatch.put(font, "Italic", new Variant(true)); // 设置为斜体
		Dispatch.put(font, "Name", new Variant("宋体")); //
		Dispatch.put(font, "Size", new Variant(12)); // 小四

		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
		Dispatch.call(selection, "TypeParagraph");// 插入一个空行
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
				.toDispatch();// 段落格式
		Dispatch.put(alignment, "Alignment", "2"); // (1:置中 2:靠右 3:靠左)

	}

	/**
	 * 从word文档中读取内容
	 * 
	 * @return
	 */
	public String readFromDocument() {
		String content = null;
		content = Dispatch.get(selection, "Text").toString();
		return content;
	}

	// 向文档中添加 一个图片，
	public void insertJpeg(String jpegFilePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
		Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();

		Dispatch.call(image, "AddPicture", jpegFilePath);
	}

	// 合并两个单元格
	public void mergeCell(Dispatch cell1, Dispatch cell2) {
		Dispatch.call(cell1, "Merge", cell2);
	}

	// 保存文档的更改
	public void save() {
		Dispatch.call(doc, "Save");
	}

	/**
	 * 把选定的内容或光标插入点向上移动
	 * 
	 * @param pos
	 *            移动的距离
	 */
	public void moveUp(int pos) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		for (int i = 0; i < pos; i++) {
			// MoveDown MoveLeft moveRight
			// moveStart ( Dispatch.call(selection, "HomeKey", new Variant(6));
			// )
			// moveEnd Dispatch.call(selection, "EndKey", new Variant(6));
			Dispatch.call(selection, "MoveUp");
		}
	}

	/**
	 * 设置文档是否可见
	 * 
	 * @param visible
	 *            是否可见标识
	 */
	public void setDocVisible(boolean visible) {
		Dispatch.put(MsWordApp, "Visible", new Variant(visible));
	}

	/**
	 * 文件保存或另存为
	 * 
	 * @param savePath
	 *            保存或另存为路径
	 */
	public void save(String savePath) {
		// Dispatch.call(doc, "SaveAs", savePath);
		Dispatch.call((Dispatch) Dispatch.call(MsWordApp, "WordBasic")
				.getDispatch(), "FileSaveAs", savePath);
	}

	/**
	 * 在指定的单元格里填写数据
	 * 
	 * @param tableIndex
	 * @param cellRowIdx
	 * @param cellColIdx
	 * @param txt
	 */

	public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,
			String txt) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // 获取表格属性
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch(); // 要填充的表格
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
				new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.put(selection, "Text", txt); // put()方法设置表格内容
	}

	/**
	 * 创建表格
	 * 
	 * @param pos
	 *            位置
	 * @param cols
	 *            列数
	 * @param rows
	 *            行数
	 */

	public void createTable(int numCols, int numRows) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // 获取表格属性
		Dispatch range = Dispatch.get(selection, "Range").toDispatch(); // 获取表格行列属性
		Dispatch newTable = Dispatch.call(tables, "Add", range,
				new Variant(numRows), new Variant(numCols)).toDispatch(); // 向表格中添加内容
		Dispatch.call(selection, "MoveRight");
	}

	/**
	 * 关闭全部应用
	 */
	public void close() {
		if (MsWordApp != null) {
			Dispatch.call(MsWordApp, "Quit");
			MsWordApp = null;
		}
		selection = null;
		documents = null;
	}

	public static void main(String[] args) {
		WordUtil wordUtils = new WordUtil();
		wordUtils.createDocument();
		// wordUtils.setDocVisible(true);
		wordUtils.save("");
		wordUtils.writeToDocument("");
		wordUtils.close();
	}
}
