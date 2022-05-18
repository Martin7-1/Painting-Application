package com.zyinnju.draw.shape;

import com.zyinnju.draw.AbstractContent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.awt.*;
import java.io.Serializable;

/**
 * 抽象的图形类
 *
 * @author Zyi
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractShape extends AbstractContent implements Serializable {

	@Override
	public void draw(Graphics2D g) {
		// 设置颜色
		g.setPaint(color);
		// 设置线宽
		g.setStroke(new BasicStroke(thickness));
		// 设置渲染算法
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
