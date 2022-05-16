package com.zyinnju.window;

import com.zyinnju.draw.AbstractContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 主面板 单例模式
 *
 * @author Zyi
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class MainFrame extends JFrame {

	private MainFrame() {
		// private constructor, just for singleton
		colorPanel = ColorPanel.getInstance();
		drawPanel = DrawPanel.getInstance();
		paintMenu = PaintMenu.getInstance();
		paintToolBar = PaintToolBar.getInstance();
	}

	public static MainFrame getInstance() {
		return InnerClass.getInstance();
	}

	private static class InnerClass {
		private static final MainFrame INSTANCE = new MainFrame();

		private static MainFrame getInstance() {
			return INSTANCE;
		}
	}

	/**
	 * 颜色选择的面板
	 */
	private ColorPanel colorPanel;
	/**
	 * 绘画面板
	 */
	private DrawPanel drawPanel;
	/**
	 * 菜单栏
	 */
	private PaintMenu paintMenu;
	/**
	 * 工具栏
	 */
	private PaintToolBar paintToolBar;

	public void init(String title) {

	}
}
