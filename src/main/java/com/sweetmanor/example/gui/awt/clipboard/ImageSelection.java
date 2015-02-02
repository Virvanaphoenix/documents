package com.sweetmanor.example.gui.awt.clipboard;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * 图像对象的封装，使图像可在剪贴板中传递
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ImageSelection implements Transferable {
	private Image image;

	public ImageSelection(Image image) {
		this.image = image;
	}

	// 返回该Transferable对象所支持的所有DataFlavor
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}

	// 返回该Transferable对象是否支持指定的DataFlavor
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return DataFlavor.imageFlavor.equals(flavor);
	}

	// 取出该Transferable对象里实际的数据
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (DataFlavor.imageFlavor.equals(flavor))
			return image;
		else
			throw new UnsupportedFlavorException(flavor);
	}

}