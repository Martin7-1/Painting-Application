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
	 *
	 */
	;

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
