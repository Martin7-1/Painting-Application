package com.zyinnju.memento;

import com.zyinnju.draw.AbstractContent;

import java.util.List;

/**
 * @author Zyi
 */
public class CareTaker {

	private CareTaker() {

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
	private List<Memento> contentList;

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
		return contentList.remove(index);
	}
}
