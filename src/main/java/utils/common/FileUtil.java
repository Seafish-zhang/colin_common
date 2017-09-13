package utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FileUtil {

	/**
	 * 复制文件
	 *
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static boolean copyFile(String oldPath, String newPath) {
		InputStream is = null;
		FileOutputStream os = null;
		try {
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				is = new FileInputStream(oldPath); // 读入原文件
				os = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean copyFileByChannel(String oldPath, String newPath) {
		try {
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				FileInputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream outStream = new FileOutputStream(newPath);
				FileChannel inChannel = inStream.getChannel();
				FileChannel outChannel = outStream.getChannel();
				inChannel.transferTo(0, inChannel.size(), outChannel);
				inChannel.close();
				outChannel.close();
				inStream.close();
				outStream.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean copyFile(InputStream is, String newPath) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(newPath);
			byte[] buffer = new byte[1024]; // 字节数
			int length;
			while ((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static synchronized boolean copyFile(InputStream is, File newFile) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024]; // 字节数
			int length;
			while ((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean copyFile(File oldfile, File newfile) {
		InputStream is = null;
		FileOutputStream os = null;
		try {
			if (oldfile.exists()) { // 文件存在时
				is = new FileInputStream(oldfile); // 读入原文件
				os = new FileOutputStream(newfile);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
