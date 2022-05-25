package com.zyinnju.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Zyi
 */
public class Images extends AbstractContent {

	/**
	 * 绘制内容所保存 or 打开的图片
	 */
	protected BufferedImage image;
	/**
	 * 绘画的面板
	 */
	protected JPanel board;

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, 0, 0, board);
	}
}
