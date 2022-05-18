package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * @author Zyi
 */
@NoArgsConstructor
@SuperBuilder
public class FillCircle extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		fillCircle(g);
	}

	private void fillCircle(Graphics2D g) {
		int radius = getRadius();
		g.fillOval(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), radius, radius);
	}

	private int getRadius() {
		return Math.max(Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()));
	}
}
