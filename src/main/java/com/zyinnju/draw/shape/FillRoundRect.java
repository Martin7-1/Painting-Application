package com.zyinnju.draw.shape;

import com.zyinnju.draw.Point;
import com.zyinnju.utils.PointUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.awt.*;

/**
 * 实心圆角矩形
 *
 * @author Zyi
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FillRoundRect extends AbstractShape {

	/**
	 * 圆角的水平直径
	 */
	private int arcWidth;
	/**
	 * 圆角的竖直直径
	 */
	private int arcHeight;

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		fillRoundRectangle(g);
	}

	@Override
	public boolean hasPoint(Point point) {
		return point.getX() <= PointUtil.getMaxPointX(startPoint, endPoint) && point.getX() >= PointUtil.getMinPointX(startPoint, endPoint)
			&& point.getY() >= PointUtil.getMinPointY(startPoint, endPoint) && point.getY() <= PointUtil.getMaxPointY(startPoint, endPoint);
	}

	private void fillRoundRectangle(Graphics2D g) {
		int width = Math.abs(startPoint.getX() - endPoint.getX());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.fillRoundRect(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height, arcWidth, arcHeight);
	}
}
