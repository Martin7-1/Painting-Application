package com.zyinnju.window;

import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.utils.StyleUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static com.zyinnju.utils.ResourcesPathUtil.*;

/**
 * 工具栏类 用于选择所要绘制的图形等
 *
 * @author Zyi
 */
@Getter
public class PaintToolBar extends JToolBar {

	private PaintToolBar() {
		initToolBar();
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
	 * 图标提示
	 */
	private static final String[] TOOL_TIP_LIST = {"保存", "清空", "撤销", "铅笔", "直线", "空心矩形", "填充矩形", "空心椭圆", "填充椭圆", "空心圆形", "填充圆形",
		"空心圆角矩形", "填充圆角矩形", "三角形", "五边形", "六边形", "橡皮擦", "笔刷", "文本", "粗细"};
	/**
	 * 字体的选择
	 */
	private static final String[] FONT_LIST = {"宋体", "隶书", "华文彩云", "仿宋_GB2312", "华文行楷", "方正舒体"};
	private static final String[] FONT_SIZE_LIST = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36",
		"48", "72"};
	/**
	 * 各种图形的选择按钮
	 */
	private JButton[] contentButtonList;
	/**
	 * 字体选择栏
	 */
	private JComboBox<String> fontComboBox;
	/**
	 * 字大小选择栏
	 */
	private JComboBox<String> fontSizeComboBox;
	/**
	 * 图标
	 */
	private ImageIcon[] imageIconList;
	/**
	 * 当前字体大小
	 */
	private int curFontSize;
	/**
	 * 当前字体名字
	 */
	private String curFontName;
	/**
	 * 是否粗体勾选框
	 */
	private Checkbox boldButton;
	/**
	 * 是否斜体勾选框
	 */
	private Checkbox italicButton;
	/**
	 * 是否斜体
	 */
	private Boolean isItalic;
	/**
	 * 是否粗体
	 */
	private Boolean isBold;
	/**
	 * 当前选择的图标 默认为pencil
	 */
	private int curChoice = 3;
	private PaintMenu menu;

	private void initToolBar() {
		menu = PaintMenu.getInstance();
		// 初始化工具栏
		contentButtonList = new JButton[RESOURCE_LIST.length];
		// 流式布局
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(StyleUtil.BACKGROUND_COLOR);
		boldButton = createCheckBox("bold");
		italicButton = createCheckBox("italic");

		initFontComboBox();
		initFontSizeComboBox();
		initImageIcon();
		setFloatable(false);
		add(fontComboBox);
		add(fontSizeComboBox);
		add(italicButton);
		add(boldButton);

		addListener();
	}

	private Checkbox createCheckBox(String title) {
		Checkbox checkbox = new Checkbox(title);
		checkbox.setBackground(StyleUtil.BACKGROUND_COLOR);
		checkbox.setPreferredSize(new Dimension(45, 30));

		return checkbox;
	}

	private void initFontComboBox() {
		fontComboBox = new JComboBox<>(FONT_LIST);
		fontComboBox.setPreferredSize(new Dimension(100, 30));
	}

	private void initFontSizeComboBox() {
		fontSizeComboBox = new JComboBox<>(FONT_SIZE_LIST);
		fontSizeComboBox.setPreferredSize(new Dimension(50, 30));
	}

	private void initImageIcon() {
		imageIconList = new ImageIcon[RESOURCE_LIST.length];
		for (int i = 0; i < RESOURCE_LIST.length; i++) {
			contentButtonList[i] = new JButton();
			imageIconList[i] = new ImageIcon(Objects.requireNonNull(getClass().getResource(RESOURCE_LIST[i])));
			contentButtonList[i].setIcon(imageIconList[i]);
			contentButtonList[i].setToolTipText(TOOL_TIP_LIST[i]);
			// 设置图标大小
			contentButtonList[i].setPreferredSize(new Dimension(28, 28));
			contentButtonList[i].setBackground(Color.WHITE);
			add(contentButtonList[i]);
		}
	}

	private void addListener() {
		// 设置监听事件
		for (int i = 2; i < RESOURCE_LIST.length; i++) {
			contentButtonList[i].addActionListener(e -> {
				for (int j = 0; j < RESOURCE_LIST.length; j++) {
					// 如果按钮被点击。则设置相应的画笔
					if (e.getSource().equals(contentButtonList[j])) {
						System.out.println("you choose: " + TOOL_TIP_LIST[j]);
						curChoice = j;
						// drawingArea.createNewGraphics();
						repaint();
					}
				}

			});
		}

		// 保存操作
		contentButtonList[0].addActionListener(e -> {
			menu.saveFile();
			GlobalStateHandler.setIsSaved(true);
		});
		// 创建新文件操作
		contentButtonList[1].addActionListener(e -> menu.createNewFile());
		// 撤销操作
		// contentButtonList[2].addActionListener(e -> drawingArea.undo());

		// 添加监听
		italicButton.addItemListener(e -> isItalic = true);
		boldButton.addItemListener(e -> isBold = true);

		// 设置字体大小
		fontSizeComboBox.addItemListener(e -> curFontSize = Integer.parseInt(FONT_SIZE_LIST[fontSizeComboBox.getSelectedIndex()]));
		// 设置字体
		fontComboBox.addItemListener(e -> curFontName = FONT_LIST[fontComboBox.getSelectedIndex()]);
	}
}
