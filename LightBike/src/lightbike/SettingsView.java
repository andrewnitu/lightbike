package lightbike;

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

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SettingsView extends JPanel {
	public static final int BUTTON_WIDTH = 300;
	public static final int BUTTON_HEIGHT = 50;
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final Font BUTTON_FONT = new Font ("Helvetica", Font.BOLD, 32);
	public static int currentButton = 0;
	
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

		IntField players = new IntField((WINDOW_WIDTH - BUTTON_WIDTH)/2, 300, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 300 + BUTTON_HEIGHT, Palette.LIME_GREEN, "Play", BUTTON_FONT, 2);
		Button back = new Button((WINDOW_WIDTH - BUTTON_WIDTH)/2, 500, (WINDOW_WIDTH - BUTTON_WIDTH)/2 + BUTTON_WIDTH, 500 + BUTTON_HEIGHT, Palette.RED, "Quit", BUTTON_FONT);
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
			if (currentButton == 0) {
				//play();
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
		
		//drawing in the background
		g2d.setColor(Palette.BLACK);
		g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		//iterate through each button and draw it
//		for (int i = 0; i < buttonList.size(); i++)
//		{
//			int xCoord1 = buttonList.get(i).getxCoord1();
//			int xCoord2 = buttonList.get(i).getxCoord2();
//			int yCoord1 = buttonList.get(i).getyCoord1();
//			int yCoord2 = buttonList.get(i).getyCoord2();
//
//			String text = buttonList.get(i).getText();
//			Color buttonColor = buttonList.get(i).getColor();
//			
//			// drawing the button
//			g2d.setColor(buttonColor);
//			g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);
//			
//			// drawing the button text
//			g2d.setFont(BUTTON_FONT);
//			FontMetrics metrics = g2d.getFontMetrics(buttonList.get(i).getFont());
//			int stringWidth = metrics.stringWidth(text);
//			int stringHeight = metrics.getHeight();
//			int stringAscent = metrics.getAscent();
//			g2d.drawString(text, xCoord1 + (xCoord2 - xCoord1 - stringWidth)/2, yCoord1 + (yCoord2 - yCoord1 - stringHeight)/2 + stringAscent);
//			
//			// drawing (or erasing) the selection border
//			if (i != currentButton) //if not iterating on the current button, set the drawing colour to black
//				g2d.setColor(Palette.BLACK);
//			g2d.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);
//		}
	}
	
	public void changeButton(int direction) { // direction == 0 -> up, direction == 1 -> down
		if (direction == 0) {
			if (currentButton != 0) {
				currentButton--;
			}
		}
		else {
//			if (currentButton != buttonList.size() - 1) {
//				currentButton++;
//			}
		}
	}
	
	public void setApp(Application app) {
	      this.application = app;
	}
}
