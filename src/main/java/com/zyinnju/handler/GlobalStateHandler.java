package com.zyinnju.handler;

/**
 * 用来记录画图工具中一些状态
 *
 * @author Zyi
 */
public class GlobalStateHandler {

	private static boolean isSaved = false;

	public static boolean getIsSaved() {
		return isSaved;
	}

	public static void setIsSaved(boolean isSaved) {
		GlobalStateHandler.isSaved = isSaved;
	}
}
