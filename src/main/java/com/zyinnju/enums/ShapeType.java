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
public enum ShapeType implements ValueEnum, Serializable {

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
	 * 图片
	 */
	IMAGE("图片");

	private static Map<String, ShapeType> SHAPE_MAP = new HashMap<>();

	static {
		for (ShapeType shapeType : ShapeType.values()) {
			SHAPE_MAP.put(shapeType.getValue(), shapeType);
		}
	}

	@Setter
	private String value;

	@Override
	public String getValue() {
		return this.value;
	}

	public static ShapeType getShapeTypeByValue(String value) {
		return SHAPE_MAP.get(value);
	}
}
