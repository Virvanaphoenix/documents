package com.sweetmanor.util.office;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;

import com.sweetmanor.util.FileUtil;

/**
 * EXCEL工具类，同时支持2003和2007格式
 * 
 * @version 1.0 2014-12-31
 * @author ijlhjj
 */
public class ExcelUtil {
	private static Workbook excel; // Excel工作簿
	private static Sheet sheet; // Excel工作表

	public static boolean INCLUDE_NULL_ROW = false;
	public static boolean INCLUDE_NULL_CELL = true;

	/**
	 * 读取文件的第一个表格数据。INCLUDE_NULL_ROW：是否包括空行，INCLUDE_NULL_CELL：是否包括空列
	 */
	public static List<List<String>> read(String filePath) throws IOException,
			InvalidFormatException {
		return read(filePath, INCLUDE_NULL_ROW, INCLUDE_NULL_CELL);
	}

	/**
	 * 读取文件的指定表格数据。INCLUDE_NULL_ROW：是否包括空行，INCLUDE_NULL_CELL：是否包括空列
	 */
	public static List<List<String>> read(String filePath, String sheetName)
			throws IOException, InvalidFormatException {
		return read(filePath, sheetName, INCLUDE_NULL_ROW, INCLUDE_NULL_CELL);
	}

	/**
	 * 读取第一个表格的单行。
	 */
	public static List<List<String>> read(String filePath, int row)
			throws IOException, InvalidFormatException {
		return read(filePath, 0, row, row, null, true, true);
	}

	/**
	 * 读取指定表格的单行。
	 */
	public static List<List<String>> read(String filePath, String sheetName,
			int row) throws IOException, InvalidFormatException {
		return read(filePath, sheetName, row, row, null, true, true);
	}

	/**
	 * 读取文件的第一个表格数据
	 */
	public static List<List<String>> read(String filePath,
			boolean includeNullRow, boolean includeNullCell)
			throws IOException, InvalidFormatException {
		return read(filePath, 0, -1, -1, null, includeNullRow, includeNullCell);
	}

	/**
	 * 读取文件的指定表格数据
	 */
	public static List<List<String>> read(String filePath, String sheetName,
			boolean includeNullRow, boolean includeNullCell)
			throws IOException, InvalidFormatException {
		return read(filePath, sheetName, -1, -1, null, includeNullRow,
				includeNullCell);
	}

	/**
	 * 读取指定文件的指定工作表
	 */
	public static List<List<String>> read(String filePath, int sheetIndex,
			int minRow, int maxRow, int[] columns, boolean includeNullRow,
			boolean includeNullCell) throws InvalidFormatException, IOException {
		openExcel(filePath);
		openSheet(sheetIndex);
		return read(minRow, maxRow, columns, includeNullRow, includeNullCell);
	}

	/**
	 * 读取指定文件的指定工作表
	 */
	public static List<List<String>> read(String filePath, String sheetName,
			int minRow, int maxRow, int[] columns, boolean includeNullRow,
			boolean includeNullCell) throws InvalidFormatException, IOException {
		openExcel(filePath);
		int sheetIndex = getIndexFromName(sheetName);
		openSheet(sheetIndex);
		return read(minRow, maxRow, columns, includeNullRow, includeNullCell);
	}

	public static Map<String, List<List<String>>> readAllSheets(String filePath)
			throws InvalidFormatException, IOException {
		openExcel(filePath);
		int sheetNum = excel.getNumberOfSheets();
		Map<String, List<List<String>>> content = new HashMap<String, List<List<String>>>();
		for (int i = 0; i < sheetNum; i++) {
			String sheetName = getNameFromIndex(i);
			openSheet(i);
			List<List<String>> sheetContent = read(-1, -1, null,
					INCLUDE_NULL_ROW, INCLUDE_NULL_CELL);
			content.put(sheetName, sheetContent);
		}
		return content;
	}

	/**
	 * 实际读取EXCEL数据的方法，读取行必须连续，列可不连续
	 * 
	 * @param minRow
	 *            读取的最小行号，包含。负值将从第一行开始读取
	 * @param maxRow
	 *            读取的最大行号，包含。负值将读取至表格最后一行
	 * @param columns
	 *            读取的表格列数组。null值将包含所有列
	 */
	private static List<List<String>> read(int minRow, int maxRow,
			int[] columns, boolean includeNullRow, boolean includeNullCell) {
		minRow = minRow < 0 ? 0 : minRow; // 包含的最小行，在最小行和第一行之间取大
		maxRow = maxRow > sheet.getLastRowNum() ? maxRow : sheet
				.getLastRowNum();// 包含的最大行，在最大行和最后一行之间取小
		boolean allColumns = false;// 是否包含所有列
		if (columns == null)
			allColumns = true;

		List<List<String>> content = new ArrayList<List<String>>();// 读取的内容
		for (int i = minRow; i <= maxRow; i++) {
			Row row = sheet.getRow(i);// 循环获取每一行
			List<String> rowContent = new ArrayList<String>();
			if (row == null) {// 处理空行
				if (includeNullRow)
					content.add(rowContent);
			} else {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {// 处理空列
						if (includeNullCell)
							rowContent.add("");
					} else {
						boolean scopeIn = allColumns;
						if (!scopeIn) {// 是否在指定包含的列范围内
							int cellIndex = cell.getColumnIndex();
							scopeIn = ArrayUtils.contains(columns, cellIndex);
						}
						if (scopeIn) {
							// 判断数据类型，目前只处理文本、数字、日期类型，其他类型不处理
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								rowContent.add(cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) {
									rowContent.add(cell.getDateCellValue()
											.toString());
								} else {
									rowContent.add(cell.getNumericCellValue()
											+ "");
								}
								break;
							default:
								if (INCLUDE_NULL_CELL)
									rowContent.add(""); // 其他类型如果包含空值就加个空字符，在合并单元格的情况下可能会添加干扰数据
							}
						}
					}
				}
				content.add(rowContent);
			}
		}
		return content;
	}

	/**
	 * 把指定内容写入EXCEL。 不覆盖，指定路径已存在文件的情况下不执行任何操作，直接返回false
	 * 
	 * @param filePath
	 *            指定文件路径
	 * @param sheetName
	 *            指定表名
	 * @param titles
	 *            标题行，仅支持单行标题
	 * @param content
	 *            List<List<String>>类型的二维集合
	 * @throws IOException
	 */
	public static boolean write(String filePath, String sheetName,
			String[] titles, List<List<String>> content) throws IOException {
		// 如果文件已存在，直接返回false
		if (FileUtil.isExist(filePath)) {
			return false;
		}
		excel = new HSSFWorkbook();
		// 以指定名称创建工作表，非法字符将替换为空格
		createSheet(sheetName);
		// 将内容写入工作表
		wirteToSheet(titles, content);
		// 将工作簿写入本地文件
		return wirteToFile(filePath);
	}

	private static void wirteToSheet(String[] titles, List<List<String>> content) {
		// 行号
		short rowNum = 0;
		// 如果传入的标题行不为空
		if (titles != null && titles.length > 0) {
			// 新建标题行
			Row row = sheet.createRow(rowNum);
			// 行号加1
			rowNum++;
			// 依次输出每列标题
			for (int i = 0; i < titles.length; i++) {
				// 创建单元格
				Cell cell = row.createCell(i);
				// 设置单元格类型为文本
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 设置单元格格式
				cell.setCellStyle(cellStyleTilte());
				// 填充每列内容
				cell.setCellValue(titles[i]);
			}
		}
		// 如果传入的内容行不为空
		if (content != null && content.size() > 0) {
			// 依次输出每行内容，隔开标题行，从第一行起
			for (int i = 0; i < content.size(); i++) {
				// 创建内容行
				Row row = sheet.createRow(rowNum);
				// 行号加1
				rowNum++;
				// 获取每行内容
				List<String> rowText = content.get(i);
				// 如果当前行内容不为空
				if (rowText != null && rowText.size() > 0) {
					// 依次输出每列内容
					for (int j = 0; j < rowText.size(); j++) {
						// 创建单元格
						Cell cell = row.createCell(j);
						// 设置单元格类型为文本
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						// 填充每列内容
						cell.setCellValue(rowText.get(j));
					}
				}
			}
		}
	}

	private static void openExcel(String filePath) throws IOException,
			InvalidFormatException {
		if (!FileUtil.isExist(filePath))
			throw new FileNotFoundException("文件不存在！");

		InputStream is = new FileInputStream(filePath);
		excel = WorkbookFactory.create(is);
	}

	private static void openSheet(int sheetIndex)
			throws IllegalArgumentException {
		int sheetNum = excel.getNumberOfSheets();
		if (sheetIndex >= sheetNum)
			throw new IllegalArgumentException("工作表不存在");
		sheet = excel.getSheetAt(sheetIndex);
	}

	private static int getIndexFromName(String sheetName) {
		return excel.getSheetIndex(sheetName);
	}

	private static String getNameFromIndex(int index) {
		return excel.getSheetName(index);
	}

	/**
	 * 以安全名称创建工作表（去除非法表名字符）
	 */
	private static void createSheet(String sheetName) {
		String safeName = "sheet1";
		if (sheetName != null && !sheetName.equals("")) {
			safeName = WorkbookUtil.createSafeSheetName(sheetName);
		}
		sheet = excel.createSheet(safeName);
	}

	/**
	 * 把工作簿写入本地文件
	 */
	private static boolean wirteToFile(String filePath) {
		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			excel.write(fileOut);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 设置标题单元格格式：垂直居中、水平居中：粗体字，24号
	 */
	private static CellStyle cellStyleTilte() {
		// 创建单元格格式
		CellStyle cellStyle = excel.createCellStyle();
		// 设置水平居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		// 创建字体格式
		Font font = excel.createFont();
		// 设置粗体字
		font.setBoldweight((short) 24);
		// 设置字体格式
		cellStyle.setFont(font);

		return cellStyle;
	}

}
