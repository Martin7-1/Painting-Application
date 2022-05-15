package com.zyinnju.shape;

/**
 * 抽象的画图工具类 - 包括画笔和刷子和橡皮擦
 * @author Zyi
 */
public abstract class AbstractPaintTool extends AbstractContent {

	/**
	 * 铅笔或橡皮擦的笔迹长度
	 */
	protected Integer length;
	/**
	 * 画图工具的粗细
	 */
	protected Integer thickness;
}
