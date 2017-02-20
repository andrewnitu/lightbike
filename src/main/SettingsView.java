package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SettingsView extends JPanel {
	public static final int BUTTON_WIDTH = 300;
	public static final int BUTTON_HEIGHT = 50;
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final Font BUTTON_FONT = new Font("Helvetica", Font.BOLD, 32);
	public static final Font TITLE_FONT = new Font("Helvetica", Font.BOLD, 128);
	public static final int TITLE_RATIO = 4;

	// Play with object descendance to consolidate into one list
	public static ArrayList<Button> buttonList = new ArrayList<Button>();
	public static ArrayList<IntField> intFieldList = new ArrayList<IntField>();

	private int currentElement = 0;

	private Application application;

	public SettingsView() {
		intializeSettings();
	}

	private void intializeSettings() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		IntField players = new IntField((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 300,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, Palette.LIME_GREEN, "Play",
				BUTTON_FONT, 2);
		Button back = new Button((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 500,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, Palette.RED, "Quit",
				BUTTON_FONT);

		intFieldList.add(players);
		buttonList.add(back);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawSettings(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			processKeyPressed(e);
		}
	}

	public void processKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			changeButton(0);
		}

		if (key == KeyEvent.VK_DOWN) {
			changeButton(1);
		}

		if (key == KeyEvent.VK_ENTER) {
			if (currentElement == 0) {
				// play();
			}
		}

		repaint();
	}

	private void back() {

	}

	// TO BE COMPLETED
	public void drawSettings(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		// drawing in the background
		g2d.setColor(Palette.BLACK);
		g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		// draw in the game name
		FontMetrics titleMetrics = g.getFontMetrics(TITLE_FONT);
		g2d.setFont(TITLE_FONT);
		g2d.setColor(Palette.BLUE);
		g2d.drawString("Settings", (WINDOW_WIDTH - titleMetrics.stringWidth("Settings")) / 2,
				WINDOW_HEIGHT / TITLE_RATIO);

		// iterate through each button and draw it
		for (int i = 0; i < buttonList.size(); i++) {
			if (i == currentElement) {
				MenuDrawing.draw(buttonList.get(i), g2d, true);
			}
			else {
				MenuDrawing.draw(buttonList.get(i), g2d, false);
			}
		}

		for (int i = 0; i < intFieldList.size(); i++) {
			if (i == currentElement) {
				MenuDrawing.draw(intFieldList.get(i), g2d, true);
			}
			else {
				MenuDrawing.draw(intFieldList.get(i), g2d, false);
			}
		}
	}

	public void changeButton(int direction) { // direction == 0 -> up, direction == 1 -> down
		if (direction == 0) {
			if (currentElement != 0) {
				currentElement--;
			}
		}
		else {
			if (currentElement != buttonList.size() - 1) {
				currentElement++;
			}
		}
	}

	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
