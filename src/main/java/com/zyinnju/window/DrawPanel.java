package com.zyinnju.window;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.draw.Point;
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
	private final CareTaker careTaker;

	private DrawPanel() {
		contentList = new ArrayList<>();
		careTaker = CareTaker.getInstance();
		setCursor(Cursor.getDefaultCursor());
		setBackground(StyleUtil.DRAW_PANEL_COLOR);
		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMotionListener());
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
		contentList.add(content);
		// 同时加入到备忘录中
		Originator originator = new Originator(content);
		careTaker.addMemento(originator.createMemento());
		content.setColor(GlobalStateHandler.getCurColor());
		content.setThickness(GlobalStateHandler.getThickness());
	}

	public void undo() {
		// 撤销到上一步
		Originator originator = new Originator();
		originator.restoreMemento(careTaker.removeMemento(careTaker.getListSize() - 1));
		System.out.println("remove: " + originator.getContent().toString());
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

	private static class InnerClass {
		private static final DrawPanel INSTANCE = new DrawPanel();
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
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

		@Override
		public void mouseReleased(MouseEvent e) {
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

		@Override
		public void mouseMoved(MouseEvent e) {
			JLabel mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
			mouseStatusBar.setText("point :[" + e.getX() + ", " + e.getY() + "]");
		}
	}
}
