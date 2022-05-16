package com.zyinnju.window;

import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.utils.ResourcesPathUtil;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private JMenuBar menuBar;
	/**
	 * 菜单栏的具体选项
	 */
	private JMenuItem[] menuItemList;
	/**
	 * 菜单栏的分类按钮
	 */
	private JMenu[] menuList;
	/**
	 * 文件路径 todo: 替换为File.Separator
	 */
	private static final String AUTHOR_INFO = "src/main/resources/msg/AuthorInfo.txt";
	private static final String USER_HELP_INFO = "src/main/resources/msg/HelpInfo.txt";

	private PaintMenu() {
		initMenu();
	}

	public static PaintMenu getInstance() {
		return InnerClass.INSTANCE;
	}

	private void initMenu() {
		// 菜单条
		menuBar = new JMenuBar();
		menuItemList = new JMenuItem[GlobalStateHandler.getStrokeSize()];
		menuList = new JMenu[]{
			new JMenu("文件"),
			new JMenu("设置"),
			new JMenu("帮助"),
			new JMenu("粗细")
		};

		initFileMenu(menuList[0]);
		initSettingMenu(menuList[1]);
		initHelpMenu(menuList[2]);
		initThickMenu(menuList[3]);

		// 实例化菜单对象
		// 定义文件、设置、帮助菜单
		// 实例化菜单项,并通过ImageIcon对象添加图片 定义文件菜单的菜单项
		JMenuItem fileItemNew = new JMenuItem("新建", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.NEW))));
		JMenuItem fileItemOpen = new JMenuItem("打开", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.OPEN))));
		JMenuItem fileItemSave = new JMenuItem("保存", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.SAVE))));
		JMenuItem fileItemExit = new JMenuItem("退出", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.EXIT))));
		// 定设置菜单的菜单项
		JMenuItem setItemColor = new JMenuItem("颜色", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.MENU_COLOR_CHOOSER))));
		JMenuItem setItemUndo = new JMenuItem("撤销", new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.UNDO))));
		JMenuItem helpItemUse = new JMenuItem("使用手册");
		JMenuItem authorInfo = new JMenuItem("关于画图");
		for (int i = 0; i < menuList.length; i++) {
			menuItemList[i] = new JMenuItem("", new ImageIcon(Objects.requireNonNull(getClass().getResource(GlobalStateHandler.getStrokeSource(i)))));
			menuList[3].add(menuItemList[i]);
		}
		authorInfo.addActionListener(e -> JOptionPane.showMessageDialog(null, getMessage(AUTHOR_INFO), "关于画图", JOptionPane.PLAIN_MESSAGE));
		helpItemUse.addActionListener(e -> JOptionPane.showMessageDialog(null, getMessage(USER_HELP_INFO), "使用说明", JOptionPane.PLAIN_MESSAGE));
		helpMenu.add(helpItemUse);
		helpMenu.add(authorInfo);
		// 设置快捷键
		fileItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		fileItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		fileItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		setItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		// 添加粗细子菜单

		// 添加菜单项到菜单
		fileMenu.add(fileItemNew);
		fileMenu.add(fileItemOpen);
		fileMenu.add(fileItemSave);
		fileMenu.add(fileItemExit);
		setMenu.add(setItemColor);
		setMenu.add(setItemUndo);
		setMenu.add(strokeMenu);

		// 添加菜单到菜单条
		menuBar.add(fileMenu);
		menuBar.add(setMenu);
		menuBar.add(helpMenu);
		// 添加菜单条
		setJMenuBar(menuBar);

		// 给文件菜单设置监听
		fileItemNew.addActionListener(e -> menu.newFile());
		fileItemSave.addActionListener(e -> {
			// 保存文件，并将标志符saved设置为1
			menu.saveFile();
			saved = 1;
		});
		fileItemOpen.addActionListener(e -> {
			// 打开文件，并将标志符saved设置为0
			menu.openFile();
			saved = 0;
		});
		fileItemExit.addActionListener(e -> {
			// 如果文件已经保存就直接退出，若果文件没有保存，提示用户选择是否退出

			if (saved == 1) {
				System.exit(0);
			} else {
				int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
				if (n == 0) {
					System.exit(0);
				}
			}
		});
		// 给设置菜单添加监听
		setItemColor.addActionListener(e -> {
			// 设置粗细
			ColorPanel.chooseColor();

		});

		setItemUndo.addActionListener(e -> {
			// 撤销
			drawingArea.undo();

		});
		menuItemList[0].addActionListener(e -> {
			stroke = 1;
			itemList[index].width = stroke;

		});
		menuItemList[1].addActionListener(e -> {
			stroke = 5;
			itemList[index].width = stroke;

		});
		menuItemList[2].addActionListener(e -> {
			stroke = 15;
			itemList[index].width = stroke;

		});
		menuItemList[3].addActionListener(e -> {
			stroke = 25;
			itemList[index].width = stroke;

		});
	}

	public void saveFile() {

	}

	public void createNewFile() {

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

	private void initFileMenu() {
		// todo
	}

	private void initSettingMenu() {
		// todo
	}

	private void initHelpMenu() {
		// todo
	}

	private void initThickMenu() {
		// todo
	}

	private void addListener() {
		
	}

	private static class InnerClass {
		private static final PaintMenu INSTANCE = new PaintMenu();
	}
}
