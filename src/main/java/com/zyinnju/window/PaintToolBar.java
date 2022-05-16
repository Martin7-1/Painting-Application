package com.zyinnju.window;

/**
 * 工具栏类 用于选择所要绘制的图形等
 *
 * @author Zyi
 */
public class PaintToolBar {

	private PaintToolBar() {

	}

	public static PaintToolBar getInstance() {
		return InnerClass.getInstance();
	}

	private static class InnerClass {
		private static final PaintToolBar INSTANCE = new PaintToolBar();

		private static PaintToolBar getInstance() {
			return INSTANCE;
		}
	}


}
