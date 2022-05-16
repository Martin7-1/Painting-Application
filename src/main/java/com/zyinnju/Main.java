package com.zyinnju;

import com.zyinnju.window.MainFrame;

/**
 * @author Zyi
 */
public class Main {

	private static final MainFrame MAIN_FRAME = MainFrame.getInstance();

	public static void main(String[] args) {
		MAIN_FRAME.init("Draw Application");
	}
}
