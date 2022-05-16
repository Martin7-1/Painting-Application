package com.zyinnju.handler;

import static com.zyinnju.utils.ResourcesPathUtil.*;

/**
 * 用来记录画图工具中一些公共的状态
 *
 * @author Zyi
 */
public class GlobalStateHandler {

	/**
	 * 选择的图标
	 */
	public static final String[] RESOURCE_LIST = {SAVE, REFRESH, UNDO, PENCIL, LINE, RECTANGLE, FILL_RECT, OVAL, FILL_OVAL
		, CIRCLE, FILL_CIRCLE, ROUND_RECT, FILL_ROUND_RECT, TRIANGLE, PENTAGON, HEXAGON, ERASER, BRUSH, FONT};
	/**
	 * 图标提示
	 */
	public static final String[] TOOL_TIP_LIST = {"保存", "清空", "撤销", "铅笔", "直线", "空心矩形", "填充矩形", "空心椭圆", "填充椭圆", "空心圆形", "填充圆形",
		"空心圆角矩形", "填充圆角矩形", "三角形", "五边形", "六边形", "橡皮擦", "笔刷", "文本", "粗细"};
	/**
	 * 字体的选择
	 */
	public static final String[] FONT_LIST = {"宋体", "隶书", "华文彩云", "仿宋_GB2312", "华文行楷", "方正舒体"};
	public static final String[] FONT_SIZE_LIST = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36",
		"48", "72"};
	/**
	 * 笔粗细的提示
	 */
	private static final String[] STROKE_LIST = new String[]{STROKE_THINNEST, STROKE_THINNER, STROKE_NORMAL, STROKE_THICKER, STROKE_THICKEST};
	/**
	 * 是否保存
	 */
	private static boolean isSaved = false;
	/**
	 * 画笔粗细
	 */
	private static int thickness = 1;
	/**
	 * 是否是粗体
	 */
	private static boolean isBoldType = false;
	/**
	 * 是否是斜体
	 */
	private static boolean isItalicType = false;
	/**
	 * 当前选择的内容索引
	 */
	private static int curContentIndex = 3;

	public static boolean isSaved() {
		return isSaved;
	}

	public static void setIsSaved(boolean isSaved) {
		GlobalStateHandler.isSaved = isSaved;
	}

	public static int getThickness() {
		return thickness;
	}

	public static void setThickness(int thickness) {
		GlobalStateHandler.thickness = thickness;
	}

	public static boolean isBoldType() {
		return isBoldType;
	}

	public static void setIsBoldType(boolean isBoldType) {
		GlobalStateHandler.isBoldType = isBoldType;
	}

	public static boolean isItalicType() {
		return isItalicType;
	}

	public static void setIsItalicType(boolean isItalicType) {
		GlobalStateHandler.isItalicType = isItalicType;
	}

	public static int getCurContentIndex() {
		return curContentIndex;
	}

	public static void setCurContentIndex(int curContentIndex) {
		GlobalStateHandler.curContentIndex = curContentIndex;
	}

	public static int getResourceSize() {
		return RESOURCE_LIST.length;
	}

	public static String getResource(int index) {
		return RESOURCE_LIST[index];
	}

	public static String getToolTip(int index) {
		return TOOL_TIP_LIST[index];
	}

	public static String getFontSize(int index) {
		return FONT_SIZE_LIST[index];
	}

	public static String getFont(int index) {
		return FONT_LIST[index];
	}

	public static int getStrokeSize() {
		return STROKE_LIST.length;
	}

	public static String getStrokeSource(int index) {
		return STROKE_LIST[index];
	}
}
