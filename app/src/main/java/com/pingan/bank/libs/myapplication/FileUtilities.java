package com.pingan.bank.libs.myapplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

/**
 * 文件工具类
 * 
 * @author Yong Zhao
 * @date 2012-8-18
 */
public class FileUtilities {
	/**
	 * 获取文件路径<br />
	 * eg. /data/data/com.example/files/
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getFileDir(Context mContext) {
		if (mContext == null) {
			return "";
		}
		return mContext.getFilesDir().getPath() + File.separator;
	}

	/**
	 * 获取缓存路径<br />
	 * eg. <li>/data/data/com.example/cache/</li> <li>
	 * /sdcard/Android/data/com.example/cache/</li>
	 * 
	 * @param mContext
	 *            上下文对象
	 * @return 缓存文件路径
	 */
	public static String getCacheDir(Context mContext) {
		if (mContext == null) {
			return "";
		}
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 挂载了外部存储器
			cachePath = mContext.getExternalCacheDir() + File.separator;
		} else {
			cachePath = mContext.getCacheDir() + File.separator;
		}
		return cachePath;
	}

	/**
	 * 获取外置目录
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getExtDir(Context mContext) {
		if (mContext == null) {
			return "";
		}
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 挂载了外部存储器
			cachePath = Environment.getExternalStorageDirectory()
					+ File.separator;
		} else {
			cachePath = File.separator;
		}
		return cachePath;
	}

	/**
	 * 根据文件判断该文件是否存在
	 * 
	 * @param context
	 * @param file
	 * @return
	 */
	public static boolean exists(Context context, String file) {
		return new File(file).exists();
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 * @param content
	 * @throws IOException
	 */
	public static void saveFile(String file, String content) throws IOException {
		if (TextUtils.isEmpty(content)) {
			return;
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(content.getBytes());
		outputStream.flush();
		outputStream.close();
	}

}
