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
		return InnerClass.getInstance();
	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();

		private static PaintMenu getInstance() {
			return INSTANCE;
		}
	}
}
