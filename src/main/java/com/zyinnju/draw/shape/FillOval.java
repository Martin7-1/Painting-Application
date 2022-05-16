package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;

import java.awt.*;

/**
 * 实心椭圆类
 *
 * @author Zyi
 */
public class FillOval extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		fillOval(g);
	}

	private void fillOval(Graphics2D g) {
		// 椭圆的长宽不一样
		int width = Math.abs(startPoint.getX() - endPoint.getX());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.fillOval(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height);
	}
}
