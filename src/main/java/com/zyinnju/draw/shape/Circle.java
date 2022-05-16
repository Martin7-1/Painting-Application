package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Circle extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		// 设置颜色
		g.setPaint(color);
		// 设置线宽
		g.setStroke(new BasicStroke(thickness));
		// 设置渲染算法
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawOval(g);
	}

	private void drawOval(Graphics2D g) {
		int radius = getRadius();
		g.drawOval(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), radius, radius);
	}

	private int getRadius() {
		return Math.max(Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()));
	}

}
