package com.zyinnju.window;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.draw.Point;
import com.zyinnju.draw.tool.AbstractPaintTool;
import com.zyinnju.enums.ContentType;
import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.utils.StyleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 绘画的画布
 *
 * @author Zyi
 */
public class DrawPanel extends JPanel {

	private DrawPanel() {
		setCursor(Cursor.getDefaultCursor());
		setBackground(StyleUtil.DRAW_PANEL_COLOR);
		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMotionListener());
	}

	public static DrawPanel getInstance() {
		return InnerClass.INSTANCE;
	}

	private static class InnerClass {
		private static final DrawPanel INSTANCE = new DrawPanel();
	}

	private List<AbstractContent> contentList;

	public AbstractContent getCurContent() {
		return contentList.get(contentList.size() - 1);
	}

	public AbstractContent getContent(int index) {
		return contentList.get(index);
	}

	public int getContentSize() {
		return contentList.size();
	}

	public void createNewGraphics() {
		// todo: 创建新的图形
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
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
			createNewGraphics();
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
