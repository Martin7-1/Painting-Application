package com.zyinnju.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ContentType implements ValueEnum, Serializable {

	/**
	 * 图片
	 */
	IMAGE("图片"),
	/**
	 * 铅笔
	 */
	PENCIL("铅笔"),
	/**
	 * 笔刷
	 */
	BRUSH("笔刷"),
	/**
	 * 橡皮
	 */
	ERASER("橡皮"),
	/**
	 * 空心圆
	 */
	CIRCLE("空心圆"),
	/**
	 * 实心圆
	 */
	FILL_CIRCLE("实心圆"),
	/**
	 * 实心椭圆
	 */
	FILL_OVAL("实心椭圆"),
	/**
	 * 实心矩形
	 */
	FILL_RECT("实心矩形"),
	/**
	 * 实心圆角矩形
	 */
	FILL_ROUND_RECT("实心圆角矩形"),
	/**
	 * 六边形
	 */
	HEXAGON("六边形"),
	/**
	 * 直线
	 */
	LINE("直线"),
	/**
	 * 椭圆
	 */
	OVAL("椭圆"),
	/**
	 * 五边形
	 */
	PENTAGON("五边形"),
	/**
	 * 矩形
	 */
	RECTANGLE("矩形"),
	/**
	 * 圆角矩形
	 */
	ROUND_RECT("圆角矩形"),
	/**
	 * 三角形
	 */
	TRIANGLE("三角形"),
	/**
	 * 文字
	 */
	TEXT("文字");

	private static Map<String, ContentType> SHAPE_MAP = new HashMap<>();

	static {
		for (ContentType contentType : ContentType.values()) {
			SHAPE_MAP.put(contentType.getValue(), contentType);
		}
	}

	@Setter
	private String value;

	public static ContentType getContentTypeByValue(String value) {
		return SHAPE_MAP.get(value);
	}

	@Override
	public String getValue() {
		return this.value;
	}
}