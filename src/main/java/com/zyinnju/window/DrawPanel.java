package com.zyinnju.window;

import javax.swing.*;

/**
 * 绘画的画布
 *
 * @author Zyi
 */
public class DrawPanel extends JPanel {

	private DrawPanel() {

	}

	public static DrawPanel getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final DrawPanel INSTANCE = new DrawPanel();
	}
}
