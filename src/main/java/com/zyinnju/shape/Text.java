package com.zyinnju.shape;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * @author Zyi
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Text extends AbstractContent {

	/**
	 * 字体大小
	 */
	private Integer fontSize;
	/**
	 * 字体
	 */
	private String fontName;
	/**
	 * 文本
	 */
	private String text;
	/**
	 * 是否是粗体
	 */
	private Boolean isBoldType;
	/**
	 * 是否是斜体
	 */
	private Boolean isItalicType;

	@Override
	public void draw(Graphics2D g) {

	}
}
