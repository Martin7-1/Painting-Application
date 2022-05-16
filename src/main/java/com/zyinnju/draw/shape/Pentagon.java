package com.zyinnju.draw.shape;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 五边形类
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pentagon extends AbstractShape{

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawPentagon(g);
	}

	private void drawPentagon(Graphics2D g) {
		int[] xPoints = getPointsX();
		int[] yPoints = getPointsY();
		g.drawPolygon(xPoints, yPoints, 5);
	}

	private int[] getPointsX() {
		return null;
	}

	private int[] getPointsY() {
		return null;
	}
}
