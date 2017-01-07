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
	public static final Color LIME_GREEN = new Color(0, 255, 0);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final int TITLE_RATIO = 4; //how far down the screen the title is (height/TITLE_RATIO)
	public static final int BUTTON_WIDTH = 300;
	public static final int BUTTON_HEIGHT = 50;
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final Font TITLE_FONT = new Font("Helvetica", Font.BOLD, 128);
	public static final Font BUTTON_FONT = new Font ("Helvetica", Font.BOLD, 32);
	
	public static ArrayList<Button> buttonList = new ArrayList<Button>();
	public static int currentButton = 0;
	
	public MainMenuView() {
		initializeMenu();
	}
	
	// with help from http://zetcode.com/tutorials/javagamestutorial/movingsprites/
	
	private void initializeMenu() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		Button play = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 300, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, LIME_GREEN, "Play", BUTTON_FONT);
		Button quit = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 500, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, RED, "Quit", BUTTON_FONT);

		buttonList.add(play);
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
	
	public void processKeyPressed(KeyEvent event) {
		 int key = event.getKeyCode();

		 if (key == KeyEvent.VK_UP) {
			 changeButton(0);
		 }

		 if (key == KeyEvent.VK_DOWN) {
			 changeButton(1);
		 }
		 
		 if (key == KeyEvent.VK_ENTER) {
			 if (currentButton == 0) {
				 play();
			 }
			 else if (currentButton == 1) {
				 quit();
			 }
		 }
		 
		 repaint();
	}
	
	private void play() {
		
	}
	
	private void settings() {
		((Application) getParent()).settings();
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
		g2d.setColor(BLACK);
		g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//draw in the game name
		FontMetrics titleMetrics = g.getFontMetrics(TITLE_FONT);
		g2d.setFont(TITLE_FONT);
		g2d.setColor(LIME_GREEN);
		g2d.drawString("LightBike", (WINDOW_WIDTH - titleMetrics.stringWidth("LightBike"))/2, WINDOW_HEIGHT/TITLE_RATIO);

		//iterate through each button and draw it
		for (int i = 0; i < buttonList.size(); i++)
		{
			int xCoord1 = buttonList.get(i).getxCoord1();
			int xCoord2 = buttonList.get(i).getxCoord2();
			int yCoord1 = buttonList.get(i).getyCoord1();
			int yCoord2 = buttonList.get(i).getyCoord2();

			String text = buttonList.get(i).getText();
			Color buttonColor = buttonList.get(i).getColor();
			
			// drawing the button
			g2d.setColor(buttonColor);
			g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);
			
			// drawing the button text
			g2d.setFont(BUTTON_FONT);
			FontMetrics metrics = g2d.getFontMetrics(buttonList.get(i).getFont());
			int stringWidth = metrics.stringWidth(text);
			int stringHeight = metrics.getHeight();
			int stringAscent = metrics.getAscent();
			g2d.drawString(text, xCoord1 + (xCoord2 - xCoord1 - stringWidth)/2, yCoord1 + (yCoord2 - yCoord1 - stringHeight)/2 + stringAscent);
			
			// drawing (or erasing) the selection border
			if (i != currentButton) //if not iterating on the current button, set the drawing colour to black
				g2d.setColor(BLACK);
			g2d.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);
		}
	}
	
	public void changeButton(int direction) { // direction == 0 -> up, direction == 1 -> down
		if (direction == 0) {
			if (currentButton != 0) {
				currentButton--;
			}
		}
		else {
			if (currentButton !=  buttonList.size() - 1) {
				currentButton++;
			}
		}
	}
}
