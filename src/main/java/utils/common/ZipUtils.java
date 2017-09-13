package utils.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class ZipUtils {


	/**
	 * 创建ZIP文件
	 *
	 * @param zipPath
	 *            生成的zip文件存在路径（包括文件名）
	 */
	/**
	 *
	 * @param dirPath
	 * @param oriFileName
	 * @param oriFileType
	 * @return
	 */
	public static String convert2Zip(String dirPath, String oriFileName, String oriFileType) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		FileInputStream fis = null;
		DataInputStream dis = null;
		File oriFile = new File(dirPath + oriFileName + "." + oriFileType);
		if (!oriFile.exists()) {
			return "";
		}
		String finalPath = dirPath + oriFileName + ".zip";
		try {
			fos = new FileOutputStream(finalPath);
			zos = new ZipOutputStream(fos);
			// 获取路径
			String path = ZipUtils.class.getResource("/").getPath();
			int indexOf = path.indexOf("webapps");
			path = path.substring(0, indexOf + 8); // 获取webapps路径
			path = path + "uploads/audio/voice/md5/";

			fis = new FileInputStream(oriFile);
			dis = new DataInputStream(new BufferedInputStream(fis));
			ZipEntry ze = new ZipEntry(oriFile.getName());
			zos.putNextEntry(ze);
			// 添加编码，如果不添加，当文件以中文命名的情况下，会出现乱码
			// ZipOutputStream的包一定是apache的ant.jar包。JDK也提供了打压缩包，但是不能设置编码
			zos.setEncoding("UTF-8");
			byte[] content = new byte[1024];
			int len;
			while ((len = fis.read(content)) != -1) {
				zos.write(content, 0, len);
				zos.flush();
			}
			return finalPath;
		} catch (Exception e) {
			return "";
		} finally {
			try {
				if (dis != null) {
					dis.close();
				}
				if (fis != null) {
					fis.close();
				}
				if (zos != null) {
					zos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
