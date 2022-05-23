package com.zyinnju.composite;

/**
 * 抽象构件类
 *
 * @author Zyi
 */
public interface Component {

	/**
	 * 添加一个构件
	 *
	 * @param c 构件
	 */
	default void add(Component c) {
		// nothing to do, just to set Leaf class not to implement
	}

	/**
	 * 移除一个构件
	 *
	 * @param c 构件
	 */
	default void remove(Component c) {
		// nothing to do, just to set Leaf class not to implement
	}

	/**
	 * 获得一个构件
	 *
	 * @param index 索引
	 * @return Component
	 */
	default Component getChild(int index) {
		return null;
	}

	/**
	 * 获得构件列表索引
	 *
	 * @return component list size
	 */
	default int getComponentSize() {
		return 0;
	}

	/**
	 * 具体的业务逻辑
	 */
	void operation();
}
