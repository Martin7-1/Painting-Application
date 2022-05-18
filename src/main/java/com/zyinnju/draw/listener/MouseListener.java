package com.zyinnju.draw.listener;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.draw.Point;
import com.zyinnju.draw.tool.AbstractPaintTool;
import com.zyinnju.enums.ContentType;
import com.zyinnju.handler.GlobalStateHandler;
import com.zyinnju.window.DrawPanel;
import com.zyinnju.window.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 适配器模式
 * 鼠标点击监听器
 *
 * @author Zyi
 */
public class MouseListener extends MouseAdapter {

	private final JLabel mouseStatusBar;

	public MouseListener() {
		mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
		AbstractContent content = DrawPanel.getInstance().getCurContent();
		content.setStartPoint(new Point(e.getX(), e.getY()));
		content.setEndPoint(new Point(e.getX(), e.getY()));

		// 如果当前选择的图形是画笔或者橡皮檫，则进行下面的操作
		ContentType curContentType = GlobalStateHandler.getCurContentType();

		if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
			((AbstractPaintTool) content).setLength(0);
			content.setStartPoint(new Point(e.getX(), e.getY()));
			content.setEndPoint(new Point(e.getX(), e.getY()));
			((AbstractPaintTool) content).addLength();
			DrawPanel.getInstance().createNewGraphics();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
		ContentType curContentType = GlobalStateHandler.getCurContentType();
		AbstractContent content = DrawPanel.getInstance().getCurContent();
		if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
			content.setStartPoint(new Point(e.getX(), e.getY()));
			((AbstractPaintTool) content).addLength();
			DrawPanel.getInstance().createNewGraphics();
		}

		content.setEndPoint(new Point(e.getX(), e.getY()));
		DrawPanel.getInstance().repaint();
		DrawPanel.getInstance().createNewGraphics();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseStatusBar.setText("point: [" + e.getX() + ", " + e.getY() + "]");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseStatusBar.setText("point：");
	}
}
