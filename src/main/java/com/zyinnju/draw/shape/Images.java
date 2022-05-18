package com.zyinnju.draw.shape;

import com.zyinnju.draw.AbstractContent;

import java.awt.*;

/**
 * @author Zyi
 */
public class Images extends AbstractContent {

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, 0, 0, board);
	}
}
