package com.zyinnju.window;

import javax.swing.*;
import java.util.List;

import static com.zyinnju.utils.ResourcesPathUtil.*;

/**
 * 工具栏类 用于选择所要绘制的图形等
 *
 * @author Zyi
 */
public class PaintToolBar {

	private PaintToolBar() {

	}

	public static PaintToolBar getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final PaintToolBar INSTANCE = new PaintToolBar();
	}

	/**
	 * 选择的图标
	 */
	private static final String[] RESOURCE_LIST = {SAVE, REFRESH, UNDO, PENCIL, LINE, RECTANGLE, FILL_RECT, OVAL, FILL_OVAL
		, CIRCLE, FILL_CIRCLE, ROUND_RECT, FILL_ROUND_RECT, TRIANGLE, PENTAGON, HEXAGON, ERASER, BRUSH, FONT};
	/**
	 * 字体的选择
	 */
	private static final String[] TEXT_CHOICE_LIST = {};
	/**
	 * 各种图形的选择按钮
	 */
	private List<JButton> shapeButtonList;
	private JComboBox<String> fontComboBox;
	private JComboBox<String> fontSizeComboBox;
}
