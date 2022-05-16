package com.zyinnju.draw.shape;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 直线类
 *
 * @author Zyi
 */
@NoArgsConstructor
@SuperBuilder
public class Line extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
	}
}
