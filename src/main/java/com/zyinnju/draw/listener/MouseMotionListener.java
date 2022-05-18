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
 * 鼠标移动监听器
 *
 * @author Zyi
 */
public class MouseMotionListener extends MouseAdapter {

	private final JLabel mouseStatusBar;

	public MouseMotionListener() {
		mouseStatusBar = MainFrame.getInstance().getMouseStatusBar();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseStatusBar.setText("point :[" + e.getX() + ", " + e.getY() + "]");
		AbstractContent content = DrawPanel.getInstance().getCurContent();
		AbstractContent lastContent = DrawPanel.getInstance().getContent(DrawPanel.getInstance().getContentSize() - 1);
		ContentType curContentType = GlobalStateHandler.getCurContentType();

		if (curContentType.equals(ContentType.ERASER) || curContentType.equals(ContentType.PENCIL) || curContentType.equals(ContentType.BRUSH)) {
			lastContent.setStartPoint(new Point(e.getX(), e.getY()));
			content.setStartPoint(new Point(e.getX(), e.getY()));
			content.setEndPoint(new Point(e.getX(), e.getY()));
			((AbstractPaintTool) content).addLength();
			DrawPanel.getInstance().createNewGraphics();
		} else {
			content.setEndPoint(new Point(e.getX(), e.getY()));
		}
		MainFrame.getInstance().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseStatusBar.setText("point :[" + e.getX() + ", " + e.getY() + "]");
	}
}
