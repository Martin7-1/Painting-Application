package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 实心矩形类
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FillRect extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		fillRectangle(g);
	}

	private void fillRectangle(Graphics2D g) {
		int width = Math.abs(startPoint.getX() - endPoint.getY());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.fillRect(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height);
	}
}
