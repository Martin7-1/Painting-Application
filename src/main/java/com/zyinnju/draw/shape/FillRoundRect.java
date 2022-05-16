package com.zyinnju.draw.shape;

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
	private Integer arcWidth;
	/**
	 * 圆角的竖直直径
	 */
	private Integer arcHeight;

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		fillRoundRectangle(g);
	}

	private void fillRoundRectangle(Graphics2D g) {
		int width = Math.abs(startPoint.getX() - endPoint.getY());
		int height = Math.abs(startPoint.getY() - endPoint.getY());
		g.fillRoundRect(PointUtil.getMinPointX(startPoint, endPoint),
			PointUtil.getMinPointY(startPoint, endPoint), width, height, arcWidth, arcHeight);
	}
}
