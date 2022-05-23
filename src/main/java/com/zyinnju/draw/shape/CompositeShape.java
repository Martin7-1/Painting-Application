package com.zyinnju.draw.shape;

import com.zyinnju.draw.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 复合图形类
 *
 * @author Zyi
 */
public class CompositeShape extends AbstractShape {

	private final List<AbstractShape> shapeList = new ArrayList<>();

	public void add(AbstractShape c) {
		shapeList.add(c);
	}

	public void remove(AbstractShape c) {
		shapeList.remove(c);
	}

	public AbstractShape getChild(int index) {
		return shapeList.get(index);
	}

	public int getComponentSize() {
		return shapeList.size();
	}

	@Override
	public void draw(Graphics2D g) {
		for (AbstractShape shape : shapeList) {
			shape.draw(g);
		}
	}

	public CompositeShape cloneSelf(CompositeShape compositeShape) {
		CompositeShape cloneCompositeShape = new CompositeShape();
		for (int i = 0; i < getComponentSize(); i++) {
			AbstractShape shape = getChild(i).clone();
			// 通过平移的距离来获得中心的点
			cloneCompositeShape.add(shape);
		}

		return cloneCompositeShape;
	}

	@Override
	public boolean hasPoint(Point point) {
		return false;
	}
}
