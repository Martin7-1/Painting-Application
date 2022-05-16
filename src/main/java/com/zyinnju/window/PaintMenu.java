package com.zyinnju.window;

import javax.swing.*;

import static com.zyinnju.utils.ResourcesPathUtil.*;

/**
 * 菜单类
 *
 * @author Zyi
 */
public class PaintMenu {

	private PaintMenu() {
		initMenu();
	}

	public static PaintMenu getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();
	}

	private JMenuBar menuBar;
	private JMenuItem[] menuItemList;

	private void initMenu() {

	}

	public void saveFile() {

	}

	public void createNewFile() {

	}
}
