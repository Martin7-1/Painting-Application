package com.zyinnju.window;

import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.utils.ResourcesPathUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 菜单类
 *
 * @author Zyi
 */
public class PaintMenu {

	/**
	 * 菜单栏
	 */
	@Getter
	private JMenuBar menuBar;
	/**
	 * 画笔粗细的具体选项
	 */
	private JMenuItem[] strokeItemList;
	/**
	 * 菜单栏的分类按钮
	 */
	private JMenu[] menuList;
	/**
	 * 颜色栏
	 */
	private final ColorPanel colorPanel;
	/**
	 * 文件路径 todo: 替换为File.Separator
	 */
	private static final String AUTHOR_INFO = "src/main/resources/msg/AuthorInfo.txt";
	private static final String USER_HELP_INFO = "src/main/resources/msg/HelpInfo.txt";

	private PaintMenu() {
		colorPanel = ColorPanel.getInstance();
		initMenu();
	}

	public static PaintMenu getInstance() {
		return InnerClass.INSTANCE;
	}

	private void initMenu() {
		// 菜单条
		menuBar = new JMenuBar();
		strokeItemList = new JMenuItem[GlobalStateHandler.getStrokeSize()];
		menuList = new JMenu[]{
			new JMenu("文件"),
			new JMenu("设置"),
			new JMenu("帮助"),
			new JMenu("粗细")
		};

		initFileMenu(menuList[0]);
		initSettingMenu(menuList[1]);
		initHelpMenu(menuList[2]);

		for (int i = 0; i < menuList.length - 1; i++) {
			menuBar.add(menuList[i]);
		}
		addListener();
	}

	public void saveFile() {
		// todo
	}

	public void createNewFile() {
		// todo
	}

	public void openFile() {
		// todo
	}

	private String getMessage(String path) {
		// 读取文件
		StringBuilder content = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String res = null;
			while ((res = reader.readLine()) != null) {
				content.append(res);
				content.append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	private void initFileMenu(JMenu menu) {
		JMenuItem fileItemNew = new JMenuItem("新建", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.NEW))));
		JMenuItem fileItemOpen = new JMenuItem("打开", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.OPEN))));
		JMenuItem fileItemSave = new JMenuItem("保存", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.SAVE))));
		JMenuItem fileItemExit = new JMenuItem("退出", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.EXIT))));

		// 设置快捷键
		fileItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		fileItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		fileItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		addNewListener(fileItemNew);
		addSaveListener(fileItemSave);
		addOpenListener(fileItemOpen);
		addExitListener(fileItemExit);

		menu.add(fileItemNew);
		menu.add(fileItemOpen);
		menu.add(fileItemSave);
		menu.add(fileItemExit);
	}

	private void initSettingMenu(JMenu menu) {
		JMenuItem setItemColor = new JMenuItem("颜色", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.MENU_COLOR_CHOOSER))));
		JMenuItem setItemUndo = new JMenuItem("撤销", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.UNDO))));
		for (int i = 0; i < strokeItemList.length; i++) {
			strokeItemList[i] = new JMenuItem("", new ImageIcon(Objects.requireNonNull(getClass().getResource(GlobalStateHandler.getStrokeSource(i)))));
			menuList[3].add(strokeItemList[i]);
		}
		setItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));

		addColorListener(setItemColor);
		addUndoListener(setItemUndo);
		addStrokeListener(menuList[3]);

		// 添加菜单项到菜单
		menu.add(setItemColor);
		menu.add(setItemUndo);
		menu.add(menuList[3]);
	}

	private void initHelpMenu(JMenu menu) {
		JMenuItem helpItemUse = new JMenuItem("使用手册");
		JMenuItem authorInfo = new JMenuItem("关于画图");
		authorInfo.addActionListener(e -> JOptionPane.showMessageDialog(null, getMessage(AUTHOR_INFO), "关于画图", JOptionPane.PLAIN_MESSAGE));
		helpItemUse.addActionListener(e -> JOptionPane.showMessageDialog(null, getMessage(USER_HELP_INFO), "使用说明", JOptionPane.PLAIN_MESSAGE));
		menu.add(helpItemUse);
		menu.add(authorInfo);
	}

	private void addListener() {
		// 给文件菜单设置监听
		// 给设置菜单添加监听


	}

	private void addNewListener(JMenuItem fileItemNew) {
		fileItemNew.addActionListener(e -> createNewFile());
	}

	private void addSaveListener(JMenuItem fileItemSave) {
		fileItemSave.addActionListener(e -> {
			// 保存文件，并将标志符saved设置为1
			saveFile();
			GlobalStateHandler.setIsSaved(true);
		});
	}

	private void addOpenListener(JMenuItem fileItemOpen) {
		fileItemOpen.addActionListener(e -> {
			// 打开文件，并将标志符saved设置为0
			openFile();
			GlobalStateHandler.setIsSaved(false);
		});

	}

	private void addExitListener(JMenuItem fileItemExit) {
		fileItemExit.addActionListener(e -> {
			// 如果文件已经保存就直接退出，若果文件没有保存，提示用户选择是否退出
			if (GlobalStateHandler.isSaved()) {
				System.exit(0);
			} else {
				int exitMark = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
				if (exitMark == 0) {
					System.exit(0);
				}
			}
		});
	}

	private void addColorListener(JMenuItem setItemColor) {
		setItemColor.addActionListener(e -> {
			// 设置粗细
			colorPanel.chooseColor();

		});
	}

	private void addUndoListener(JMenuItem setItemUndo) {
		setItemUndo.addActionListener(e -> {
			// 撤销
//			drawingArea.undo();
			// todo: 备忘录模式实现撤销
		});
	}

	private void addStrokeListener(JMenu strokeMenu) {
		Map<Integer, String> map = initStrokeMap();
		for (int i = 0; i < strokeItemList.length; i++) {
			int tempI = i;
			strokeItemList[i].addActionListener(e -> {
				GlobalStateHandler.setThickness(map.get(tempI));
			});
		}
	}

	private Map<Integer, String> initStrokeMap() {
		Map<Integer, String> map = new HashMap<>(4);
		map.put(0, "thinnest");
		map.put(1, "thinner");
		map.put(2, "thicker");
		map.put(3, "thickest");
		return map;
	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();
	}
}
