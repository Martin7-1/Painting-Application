package com.zyinnju.draw.shape;

import com.zyinnju.utils.PointUtil;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 实心矩形类
 *
 * @author Zyi
 */
@NoArgsConstructor
@SuperBuilder
public class FillRect extends AbstractShape {

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		fillRectangle(g);
	}

	private void fillRectangle(Graphics2D g) {
		int width = Math.abs(startPoint.getX() - endPoint.getY());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.fillRect(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height);
	}
}
