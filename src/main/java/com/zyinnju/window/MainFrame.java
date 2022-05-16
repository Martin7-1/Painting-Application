package com.zyinnju.window;

import com.zyinnju.utils.StyleUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

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
		mouseStatusBar = new JLabel();
	}

	public static MainFrame getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final MainFrame INSTANCE = new MainFrame();
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
	/**
	 * 鼠标状态
	 */
	private JLabel mouseStatusBar;
	/**
	 * 是否保存
	 */
	private Boolean isSaved = false;

	public void init(String title) {
		// 设置标题
		this.setTitle(title);
		// 设置窗口大小
		this.setSize(950, 600);
		// 居中显示
		this.setLocationRelativeTo(null);
		add(colorPanel, BorderLayout.WEST);

		// 设置窗体图标
		setIcon();
		// 创建各种基本图形的按钮
		this.add(drawPanel, BorderLayout.CENTER);
		this.add(mouseStatusBar, BorderLayout.SOUTH);
		mouseStatusBar.setText("坐标");

		// 由于JLable是透明的，当我们把JLabel控件加载到JPanel控件之上时， 会发现JLabel的背景色总是和JPanel的背景色保持一致,
		// 设置该组件为透明
		mouseStatusBar.setOpaque(true);
		mouseStatusBar.setBackground(StyleUtil.BACKGROUND_COLOR);
		// drawPanel.createNewGraphics();

		addClosingEvent();
	}

	private void addClosingEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!isSaved) {
					int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
					if (n == 0) {
						System.exit(0);
					}
				} else {
					System.exit(0);
				}
			}
		});
	}

	private void setIcon() {
		try {
			ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("main/resources/img/themeicon.png")));
			Image image = imageIcon.getImage();
			this.setIconImage(image);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "图标异常");
		}
	}
}
