package com.sweetmanor.demo;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sweetmanor.common.CommonParam;

/**
 * Dom4j示例：Dom4j读取xml文件
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class Dom4jDemo1 {

	// 解析元素
	public static void parse(Element ele) {
		parseAttribute(ele); // 处理当前元素的属性
		@SuppressWarnings("rawtypes")
		List el = ele.elements(); // 获取所有子元素
		for (Object e : el) {
			Element element = (Element) e;
			// 如果该元素内容不是只有字符串，递归调用
			if (!element.isTextOnly()) {
				parse(element);
			} else {
				parseAttribute(element);
				System.out.println(element.getQName().getName() + "--->"
						+ element.getText()); // 获取当前元素的内容
			}
		}
	}

	// 解析元素的属性
	public static void parseAttribute(Element ele) {
		@SuppressWarnings("rawtypes")
		List attList = ele.attributes(); // 获取所有属性
		for (Object e : attList) {
			Attribute attr = (Attribute) e;
			System.out.println(ele.getQName().getName() + "元素的"
					+ attr.getQName().getName() + "属性值为：" + attr.getValue()); // 获取当前属性的属性值
		}
	}

	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(CommonParam.resourcePath
				+ "web.xml"));
		Element root = document.getRootElement();
		parse(root);
	}

}
