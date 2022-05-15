package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * 矩形
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		drawRectangle(g);
	}

	private void drawRectangle(Graphics2D g) {
		int width = Math.abs(startPoint.getX() - endPoint.getY());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.drawRect(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height);
	}
}
