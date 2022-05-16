package com.zyinnju.window;

/**
 * 菜单类
 *
 * @author Zyi
 */
public class PaintMenu {

	private PaintMenu() {

	}

	public static PaintMenu getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();
	}

	public void saveFile() {

	}

	public void createNewFile() {

	}
}
