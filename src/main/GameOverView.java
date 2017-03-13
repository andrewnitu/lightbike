package main;

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
public class GameOverView extends JPanel {
	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;
	
	private final Font GAME_OVER_FONT = new Font("Helvetica", Font.BOLD, 128);
	
	private Application application;
	
	public void start() {
		initializeGameOver();
		resume();
	}
	
	public void resume() {
		requestFocus();
	}
	
	public void initializeGameOver() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}
	
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			//processKeyPressed(e); NOT IMPLEMENTED YET
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawGameOver(g);

		Toolkit.getDefaultToolkit().sync();
	}
	
	public void drawGameOver(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		// draw in the game over text
		FontMetrics titleMetrics = g.getFontMetrics(GAME_OVER_FONT);
		g2d.setFont(GAME_OVER_FONT);
		g2d.setColor(Palette.WHITE);
		g2d.drawString("Game Over!", (WINDOW_WIDTH - titleMetrics.stringWidth("Game Over!")) / 2,
				WINDOW_HEIGHT / 3);

		// iterate through each button and draw it
		//for (int i = 0; i < buttonList.size(); i++) {
		//	if (i == currentElement) {
		//		MenuDrawing.draw(buttonList.get(i), g2d, true);
		//	}
		//	else {
		//		MenuDrawing.draw(buttonList.get(i), g2d, false);
		//	}
		//}
	}
	
	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
