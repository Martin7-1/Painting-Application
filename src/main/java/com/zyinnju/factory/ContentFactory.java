package com.zyinnju.factory;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.enums.ContentType;

/**
 * 绘图内容工厂类
 * 简单工程设计模式
 *
 * @author Zyi
 */
public class ContentFactory {

	/**
	 * 工厂方法
	 *
	 * @param contentType 当前的内容类型
	 * @return 创建出来的绘图内容
	 */
	public static AbstractContent createContent(ContentType contentType) {
		return null;
	}
}
