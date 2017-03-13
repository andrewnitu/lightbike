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
import java.util.Arrays;

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
	public static ArrayList<Element> elementList = new ArrayList<Element>();

	private int currentElement = 0;

	private int currentIntFieldValue = 0; // used with set/get methods of IntField
	private int newIntFieldValue = 0;

	private Application application;

	public SettingsView() {

	}

	public void start() {
		intializeSettings();
		resume();
	}

	public void resume() {
		currentElement = 0;
		requestFocus();
	}

	private void intializeSettings() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		IntField players = new IntField((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 300,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, Palette.LIME_GREEN, BUTTON_FONT,
				1, 1, 4);
		SelectField size = new SelectField((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 400,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 400 + BUTTON_HEIGHT, Palette.MAGENTA, BUTTON_FONT,
				new ArrayList<String>(Arrays.asList("Small", "Medium", "Large")), 0);
		Button back = new Button((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 500,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, Palette.RED, "Back",
				BUTTON_FONT);

		elementList.add(players);
		elementList.add(size);
		elementList.add(back);
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

		if (currentElement == 0) {
			if (key == KeyEvent.VK_RIGHT) {
				changeValue(1);
			}

			if (key == KeyEvent.VK_LEFT) {
				changeValue(-1);
			}
		}

		if (currentElement == 1) {
			if (key == KeyEvent.VK_RIGHT) {
				changeValue(1);
			}

			if (key == KeyEvent.VK_LEFT) {
				changeValue(-1);
			}
		}

		if (currentElement == 2) {
			if (key == KeyEvent.VK_ENTER) {
				backToMainMenu();
			}
		}

		repaint();
	}

	private void backToMainMenu() {
		if (application != null) {
			application.size = ((SelectField) (elementList.get(1))).getText();
			application.players = ((IntField) (elementList.get(0))).getValue();
			application.swapMainMenu();
		}
	}

	private void changeValue(int num) {
		if (elementList.get(currentElement) instanceof IntField) {
			((IntField) elementList.get(currentElement)).changeValue(num);
		}
		else if (elementList.get(currentElement) instanceof SelectField) {
			if (num == 1) {
				((SelectField) elementList.get(currentElement)).next();
			}
			else {
				((SelectField) elementList.get(currentElement)).previous();
			}
		}
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

		// draw in the screen name
		FontMetrics titleMetrics = g.getFontMetrics(TITLE_FONT);
		g2d.setFont(TITLE_FONT);
		g2d.setColor(Palette.BLUE);
		g2d.drawString("Settings", (WINDOW_WIDTH - titleMetrics.stringWidth("Settings")) / 2,
				WINDOW_HEIGHT / TITLE_RATIO);

		// draw in # players title
		g2d.setFont(BUTTON_FONT);
		g2d.setColor(Palette.LIME_GREEN);
		g2d.drawString("Players:", elementList.get(0).getxCoord1() - 180, elementList.get(0).getyCoord1() + 34);
		
		// draw in player size title
		g2d.setFont(BUTTON_FONT);
		g2d.setColor(Palette.MAGENTA);
		g2d.drawString("Width:", elementList.get(1).getxCoord1() - 180, elementList.get(1).getyCoord1() + 34);

		// iterate through each button and draw it
		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i) instanceof Button) {
				if (i == currentElement) {
					MenuDrawing.draw((Button) elementList.get(i), g2d, true);
				}
				else {
					MenuDrawing.draw((Button) elementList.get(i), g2d, false);
				}
			}
			if (elementList.get(i) instanceof SelectField) {
				if (i == currentElement) {
					MenuDrawing.draw((SelectField) elementList.get(i), g2d, true);
				}
				else {
					MenuDrawing.draw((SelectField) elementList.get(i), g2d, false);
				}
			}
			else if (elementList.get(i) instanceof IntField) {
				if (i == currentElement) {
					MenuDrawing.draw((IntField) elementList.get(i), g2d, true);
				}
				else {
					MenuDrawing.draw((IntField) elementList.get(i), g2d, false);
				}
			}
		}
	}

	public void changeButton(int direction) { // direction == 0 -> up, direction == 1 -> down
		if (direction == 0) {
			if (currentElement != 0) {
				if (Application.DEBUG)
					System.out.println("currentElement-- in SettingsView");
				currentElement--;
			}
		}
		else {
			if (currentElement != elementList.size() - 1) {
				if (Application.DEBUG)
					System.out.println("currentElement++ in SettingsView");
				currentElement++;
			}
		}
	}

	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
