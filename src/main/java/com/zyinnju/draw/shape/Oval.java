package com.zyinnju.draw.shape;

import com.zyinnju.draw.Point;
import com.zyinnju.utils.PointUtil;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 椭圆类 与圆的差别是分为长径和短径
 *
 * @author Zyi
 */
@NoArgsConstructor
@SuperBuilder
public class Oval extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		drawOval(g);
	}

	@Override
	public boolean hasPoint(Point point) {
		return false;
	}

	private void drawOval(Graphics2D g) {
		// 椭圆的长宽不一样
		int width = Math.abs(startPoint.getX() - endPoint.getX());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.drawOval(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height);
	}
}
