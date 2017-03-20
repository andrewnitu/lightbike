package main;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuView extends JPanel {
	private final int TITLE_RATIO = 4; // how far down the screen the title is (height/TITLE_RATIO)
	private final int BUTTON_WIDTH = 300;
	private final int BUTTON_HEIGHT = 50;
	private final Font TITLE_FONT = new Font("Helvetica", Font.BOLD, 128);
	private final Font BUTTON_FONT = new Font("Helvetica", Font.BOLD, 30);

	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;

	private ArrayList<Button> buttonList = new ArrayList<Button>();
	private int currentElement = 0;

	private Application application;

	public MainMenuView() {
	}
	
	public void start() {
		initializeMenu();
		resume();
	}
	
	public void resume() {
		requestFocus();
		//currentElement = 0; // Reset which element is selected, if desired
	}

	private void initializeMenu() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		Button play = new Button((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 300,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, Palette.BLUE, "Play",
				BUTTON_FONT);
		Button settings = new Button((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 400,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 400 + BUTTON_HEIGHT, Palette.BLUE, "Settings",
				BUTTON_FONT);
		Button quit = new Button((WINDOW_WIDTH - BUTTON_WIDTH) / 2, 500,
				(WINDOW_WIDTH - BUTTON_WIDTH) / 2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, Palette.BLUE, "Quit",
				BUTTON_FONT);

		buttonList.add(play);
		buttonList.add(settings);
		buttonList.add(quit);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawMainMenu(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			processKeyPressed(e);
		}
	}

	// decides what to do with the pressed key
	public void processKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		if (Application.DEBUG) System.out.println("Key pressed in MainMenuView.java");

		if (key == KeyEvent.VK_UP) {
			changeButton(0);
		}
		else if (key == KeyEvent.VK_DOWN) {
			changeButton(1);
		}
		else if (key == KeyEvent.VK_ENTER) {
			if (currentElement == 0) {
				play();
			}
			else if (currentElement == 1) {
				settings();
			}
			else if (currentElement == 2) {
				quit();
			}
		}

		repaint();
	}

	private void play() {
		if (application != null) {
			application.swapPlay();
		}
	}

	private void settings() {
		if (application != null) {
			application.swapSettings();
		}
	}

	private void quit() {
		System.exit(0);
	}

	public void drawMainMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		// draw in the game name
		FontMetrics titleMetrics = g.getFontMetrics(TITLE_FONT);
		g2d.setFont(TITLE_FONT);
		g2d.setColor(Palette.GREEN);
		g2d.drawString("LightBike", (WINDOW_WIDTH - titleMetrics.stringWidth("LightBike")) / 2,
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
