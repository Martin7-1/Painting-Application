package com.zyinnju.window;

import javax.swing.*;

/**
 * 菜单类
 *
 * @author Zyi
 */
public class PaintMenu {

	private JMenuBar menuBar;
	private JMenuItem[] menuItemList;

	private PaintMenu() {
		initMenu();
	}

	public static PaintMenu getInstance() {
		return InnerClass.INSTANCE;
	}

	private void initMenu() {

	}

	public void saveFile() {

	}

	public void createNewFile() {

	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();
	}
}
