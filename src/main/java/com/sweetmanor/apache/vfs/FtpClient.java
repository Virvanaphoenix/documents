package com.sweetmanor.apache.vfs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;

public class FtpClient {

	public static void main(String[] args) {

		try {
			FileSystemManager manager = VFS.getManager();

			FileObject ftpFile = manager
					.resolveFile("ftp://192.168.254.131/kettle/pentaho-kettle-5.2.zip");
			FileContent content = ftpFile.getContent();
			InputStream is = content.getInputStream();

			OutputStream os = new FileOutputStream("d:/pe.zip");

			IOUtils.copy(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
