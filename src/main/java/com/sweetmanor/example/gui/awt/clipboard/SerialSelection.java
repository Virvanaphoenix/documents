package com.sweetmanor.example.gui.awt.clipboard;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

/**
 * 可序列化对象的封装，使对象可在剪贴板中传递
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SerialSelection implements Transferable {
	private Serializable obj;

	public SerialSelection(Serializable obj) {
		this.obj = obj;
	}

	// 返回该Transferable对象所支持的所有DataFlavor
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] flavors = new DataFlavor[2];
		@SuppressWarnings("rawtypes")
		Class clazz = obj.getClass(); // 获取被封装对象的类型
		try {
			flavors[0] = new DataFlavor(DataFlavor.javaSerializedObjectMimeType
					+ ";class=" + clazz.getName());
			flavors[1] = DataFlavor.stringFlavor;
			return flavors;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 返回该Transferable对象是否支持指定的DataFlavor
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.stringFlavor)
				|| (flavor.getPrimaryType().equals("application")
						&& flavor.getSubType().equals(
								"x-java-serialized-object") && flavor
						.getRepresentationClass().isAssignableFrom(
								obj.getClass()));
	}

	// 取出该Transferable对象里实际的数据
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (!isDataFlavorSupported(flavor))
			throw new UnsupportedFlavorException(flavor);
		if (flavor.equals(DataFlavor.stringFlavor))
			return obj.toString();
		return obj;
	}

}
