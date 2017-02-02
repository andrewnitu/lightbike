package lightbike;

import java.awt.Color;
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
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")

// Main class
public class MainMenuView extends JPanel {
	public static final int TITLE_RATIO = 4; //how far down the screen the title is (height/TITLE_RATIO)
	public static final int BUTTON_WIDTH = 300;
	public static final int BUTTON_HEIGHT = 50;
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final Font TITLE_FONT = new Font("Helvetica", Font.BOLD, 128);
	public static final Font BUTTON_FONT = new Font ("Helvetica", Font.BOLD, 30);
	
	public static ArrayList<Button> elementList = new ArrayList<Button>();
	private int currentElement = 0;
	
	private Application application;
	
	public MainMenuView() {
		initializeMenu();
	}
	
	// with help from http://zetcode.com/tutorials/javagamestutorial/movingsprites/
	
	private void initializeMenu() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		Button play = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 300, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, Palette.LIME_GREEN, "Play", BUTTON_FONT);
		Button settings = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 400, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 400 + BUTTON_HEIGHT, Palette.BLUE, "Settings", BUTTON_FONT);
		Button quit = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 500, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, Palette.RED, "Quit", BUTTON_FONT);

		elementList.add(play);
		elementList.add(settings);
		elementList.add(quit);
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
            application.swapView("Play");
         }
	}
	
	private void settings() {
		if (application != null) {
            application.swapView("Settings");
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
		
		//drawing in the background
		g2d.setColor(Palette.BLACK);
		g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//draw in the game name
		FontMetrics titleMetrics = g.getFontMetrics(TITLE_FONT);
		g2d.setFont(TITLE_FONT);
		g2d.setColor(Palette.LIME_GREEN);
		g2d.drawString("LightBike", (WINDOW_WIDTH - titleMetrics.stringWidth("LightBike"))/2, WINDOW_HEIGHT/TITLE_RATIO);

		//iterate through each button and draw it
		for (int i = 0; i < elementList.size(); i++)
		{
			if (i == currentElement) {
			Drawing.draw(elementList.get(i), g2d, true);
			}
			else {
				Drawing.draw(elementList.get(i), g2d, false);
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
			if (currentElement != elementList.size() - 1) {
				currentElement++;
			}
		}
	}
	
	public void setApp(Application app) {
	      this.application = app;
	}
}
