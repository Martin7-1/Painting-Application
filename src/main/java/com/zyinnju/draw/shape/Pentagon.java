package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 五边形类
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pentagon extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		drawPentagon(g);
	}

	private void drawPentagon(Graphics2D g) {
		int[] xPoints = getPointsX();
		int[] yPoints = getPointsY();
		g.drawPolygon(xPoints, yPoints, 5);
	}

	private int[] getPointsX() {
		int xGap = Math.abs(startPoint.getX() - endPoint.getX());
		// 中点
		int pointOne = PointUtil.getMinPointX(startPoint, endPoint) + xGap / 2;
		int pointTwo = PointUtil.getMaxPointX(startPoint, endPoint);
		int pointThree = PointUtil.getMaxPointY(startPoint, endPoint) - (int) (xGap / (2 * Math.sqrt(3)));
		int pointFour = PointUtil.getMinPointX(startPoint, endPoint) + (int) (xGap / (2 * Math.sqrt(3)));
		int pointFive = PointUtil.getMinPointX(startPoint, endPoint);

		return new int[]{pointOne, pointTwo, pointThree, pointFour, pointFive};
	}

	private int[] getPointsY() {
		int yGap = Math.abs(startPoint.getY() - endPoint.getY());
		// 中点
		int pointOne = PointUtil.getMinPointY(startPoint, endPoint);
		int pointTwo = PointUtil.getMinPointY(startPoint, endPoint) + (int) (yGap / 2 * Math.sqrt(3));
		int pointThree = PointUtil.getMaxPointY(startPoint, endPoint);

		return new int[]{pointOne, pointTwo, pointThree, pointThree, pointTwo};
	}
}
