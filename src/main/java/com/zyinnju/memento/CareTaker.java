package com.zyinnju.memento;

import com.zyinnju.draw.AbstractContent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zyi
 */
public class CareTaker {

	private CareTaker() {
		contentList = new ArrayList<>();
	}

	public static CareTaker getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final CareTaker INSTANCE = new CareTaker();
	}

	/**
	 * 存储绘制的图形列表
	 */
	private final List<Memento> contentList;

	public Memento getMemento(int index) {
		return contentList.get(index);
	}

	public int getListSize() {
		return contentList.size();
	}

	public void addMemento(Memento memento) {
		contentList.add(memento);
	}

	public Memento removeMemento(int index) {
		if (index >= 0 && index < getListSize()) {
			return contentList.remove(index);
		} else {
			return null;
		}
	}
}
