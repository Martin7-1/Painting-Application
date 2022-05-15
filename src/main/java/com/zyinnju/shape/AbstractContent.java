package com.zyinnju.shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 抽象的绘制内容类
 *
 * @author Zyi
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractContent implements Serializable {

	/**
	 * 绘制内容的位置
	 */
	protected Point point;
	/**
	 * 绘制内容的颜色
	 */
	protected Color color;
	/**
	 * 绘制内容所保存 or 打开的图片
	 */
	protected BufferedImage image;

	/**
	 * 绘制内容方法
	 *
	 * @param g 画笔
	 */
	protected abstract void draw(Graphics2D g);

}
