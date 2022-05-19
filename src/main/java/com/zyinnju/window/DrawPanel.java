package com.zyinnju.window;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.draw.Point;
import com.zyinnju.draw.shape.AbstractShape;
import com.zyinnju.draw.tool.AbstractPaintTool;
import com.zyinnju.enums.ContentType;
import com.zyinnju.factory.ContentFactory;
import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.memento.CareTaker;
import com.zyinnju.memento.Originator;
import com.zyinnju.utils.ResourcesPathUtil;
import com.zyinnju.utils.StyleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 绘画的画布
 *
 * @author Zyi
 */
public class DrawPanel extends JPanel {

	/**
	 * 绘制内容的列表
	 */
	private final List<AbstractContent> contentList;
	/**
	 * 撤销功能负责人
	 */
	private final CareTaker careTaker;
	/**
	 * 弹出选择窗
	 */
	private final JPopupMenu copyMenu;
	/**
	 * 复制的图形
	 */
	private AbstractShape copyShape;
	/**
	 * 复制时点击的位置
	 */
	private Point copyPoint;

	private DrawPanel() {
		contentList = new ArrayList<>();
		careTaker = CareTaker.getInstance();
		copyMenu = new JPopupMenu();
		setCursor(Cursor.getDefaultCursor());
		setBackground(StyleUtil.DRAW_PANEL_COLOR);
		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMotionListener());

		initPopupMenu();
		add(copyMenu);
	}

	public static DrawPanel getInstance() {
		return InnerClass.INSTANCE;
	}

	public AbstractContent getCurContent() {
		return contentList.get(contentList.size() - 1);
	}

	public AbstractContent getContent(int index) {
		return contentList.get(index);
	}

	public int getContentSize() {
		return contentList.size();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 定义画板
		Graphics2D g2d = (Graphics2D) g;
		for (AbstractContent content : contentList) {
			draw(g2d, content);
		}
	}

	public void createNewGraphics() {
		ContentType curContentType = GlobalStateHandler.getCurContentType();
		// 设置光标
		if (curContentType.equals(ContentType.ERASER)) {
			// 如果是橡皮的话
			try {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(ResourcesPathUtil.CURSOR))).getImage();
				Cursor cursor = tk.createCustomCursor(image, new java.awt.Point(10, 10), "norm");
				setCursor(cursor);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "自定义光标异常");
			}
		} else if (curContentType.equals(ContentType.TEXT)) {
			setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		} else {
			setCursor(Cursor.getDefaultCursor());
		}

		AbstractContent content = ContentFactory.createContent(curContentType);
		if (content != null && isSaveType(curContentType)) {
			contentList.add(content);
			// 同时加入到备忘录中
			Originator originator = new Originator(content);
			careTaker.addMemento(originator.createMemento());
			content.setColor(GlobalStateHandler.getCurColor());
			content.setThickness(GlobalStateHandler.getThickness());
		}
	}

	public void undo() {
		// 撤销到上一步
		Originator originator = new Originator();
		originator.restoreMemento(careTaker.removeMemento(careTaker.getListSize() - 1));
		// 同时移除该类存内容的列表
		if (getContentSize() > 0) {
			contentList.remove(getContentSize() - 1);
		}
		repaint();
	}

	private void draw(Graphics2D g2d, AbstractContent abstractShape) {
		// 将画笔传入到各个子类中，用来完成各自的绘图
		abstractShape.draw(g2d);
	}

	private void initPopupMenu() {
		JMenuItem copyItem = new JMenuItem("复制");
		JMenuItem pasteItem = new JMenuItem("粘贴");
		copyItem.setBackground(StyleUtil.BACKGROUND_COLOR);
		pasteItem.setBackground(StyleUtil.BACKGROUND_COLOR);
		addCopyListener(copyItem);
		addPasteListener(pasteItem);
		copyMenu.add(copyItem);
		copyMenu.add(pasteItem);
	}

	private void addCopyListener(JMenuItem item) {
		// 点击的话就复制当前的图形
		item.addActionListener(e -> {
			Point clickPoint = new Point(copyPoint.getX(), copyPoint.getY());
			copyShape = getClickContent(clickPoint);
		});
	}

	private void addPasteListener(JMenuItem item) {
		item.addActionListener(e -> {
			// 绘制图形
		});
	}

	private boolean isSaveType(ContentType contentType) {
		return !contentType.equals(ContentType.CHOOSE);
	}

	private AbstractShape getClickContent(Point point) {
		// 点击点在图案范围内的作为选择的标准
		// fixme: 图案重叠如何选择?

		for (AbstractContent content : contentList) {
			// fixme: 不应该使用运行时的类型判断
			if (content instanceof AbstractShape) {
				AbstractShape shape = (AbstractShape) content;
				if (shape.hasPoint(point)) {
					return shape;
				}
			}
		}

		return null;
	}

	private static class InnerClass {
		private static final DrawPanel INSTANCE = new DrawPanel();
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (GlobalStateHandler.getCurContentType().equals(ContentType.CHOOSE)) {
				super.mouseClicked(e);
				int input = e.getButton();
				if (input == MouseEvent.BUTTON3) {
					// 如果按的是右键
					// 需要跳出一个选择菜单来复制
					// 获得当前选择的图标
					copyMenu.show(DrawPanel.this, e.getX(), e.getY());
					copyPoint = new Point(e.getX(), e.getY());
				}
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!GlobalStateHandler.getCurContentType().equals(ContentType.CHOOSE)) {
				createNewGraphics();
				JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
				mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
				AbstractContent content = getCurContent();
				content.setStartPoint(new Point(e.getX(), e.getY()));
				content.setEndPoint(new Point(e.getX(), e.getY()));

				// 如果当前选择的图形是画笔或者橡皮檫，则进行下面的操作
				ContentType curContentType = GlobalStateHandler.getCurContentType();

				if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
					((AbstractPaintTool) content).setLength(0);
					content.setStartPoint(new Point(e.getX(), e.getY()));
					content.setEndPoint(new Point(e.getX(), e.getY()));
					((AbstractPaintTool) content).addLength();
					createNewGraphics();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (!GlobalStateHandler.getCurContentType().equals(ContentType.CHOOSE)) {
				JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
				mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
				ContentType curContentType = GlobalStateHandler.getCurContentType();
				AbstractContent content = getCurContent();
				if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
					content.setStartPoint(new Point(e.getX(), e.getY()));
					((AbstractPaintTool) content).addLength();
					createNewGraphics();
				}

				content.setEndPoint(new Point(e.getX(), e.getY()));
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
			mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
			mouseStatusBar.setText("point：");
		}
	}

	private class MouseMotionListener extends MouseAdapter {

		/**
		 * 该事件是用于 铅笔 笔刷 橡皮擦使用
		 *
		 * @param e Mouse Event
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			if (!GlobalStateHandler.getCurContentType().equals(ContentType.CHOOSE)) {
				JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
				mouseStatusBar.setText("point :[" + e.getX() + ", " + e.getY() + "]");
				AbstractContent content = getCurContent();
				AbstractContent lastContent = getContent(getContentSize() - 1);
				ContentType curContentType = GlobalStateHandler.getCurContentType();

				if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
					lastContent.setStartPoint(new Point(e.getX(), e.getY()));
					content.setStartPoint(new Point(e.getX(), e.getY()));
					content.setEndPoint(new Point(e.getX(), e.getY()));
					((AbstractPaintTool) content).addLength();
					createNewGraphics();
				} else {
					// 除了上述的三种像素级的，其他只需要更新当前左边即可
					content.setEndPoint(new Point(e.getX(), e.getY()));
				}
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
			mouseStatusBar.setText("point :[" + e.getX() + ", " + e.getY() + "]");
		}
	}
}
