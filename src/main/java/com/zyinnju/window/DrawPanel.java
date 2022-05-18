package com.zyinnju.window;

import com.zyinnju.draw.AbstractContent;
import com.zyinnju.draw.listener.MouseListener;
import com.zyinnju.draw.listener.MouseMotionListener;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 绘画的画布
 *
 * @author Zyi
 */
public class DrawPanel extends JPanel {

	private DrawPanel() {
		setCursor(Cursor.getDefaultCursor());
		setBackground(Color.WHITE);
		addMouseListener(new MouseListener());
		addMouseMotionListener(new MouseMotionListener());
	}

	public static DrawPanel getInstance() {
		return InnerClass.INSTANCE;
	}

	public void createNewGraphics() {

	}

	private static class InnerClass {
		private static final DrawPanel INSTANCE = new DrawPanel();
	}

	private List<AbstractContent> contentList;

	public AbstractContent getCurContent() {
		return contentList.get(contentList.size() - 1);
	}

	public int getContentSize() {
		return contentList.size();
	}
}
