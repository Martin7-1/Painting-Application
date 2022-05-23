package com.zyinnju.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合类
 *
 * @author Zyi
 */
public class Composite implements Component {

	private final List<Component> componentList = new ArrayList<>();

	@Override
	public void add(Component c) {
		componentList.add(c);
	}

	@Override
	public void remove(Component c) {
		componentList.remove(c);
	}

	@Override
	public Component getChild(int index) {
		return componentList.get(index);
	}

	@Override
	public int getComponentSize() {
		return componentList.size();
	}

	@Override
	public void operation() {
		// todo: 这里的业务逻辑其实就是绘制出图形
		for (Component component : componentList) {
			component.operation();
		}
	}
}
