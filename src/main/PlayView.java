package main;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PlayView extends JPanel {
	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;
	
	private Timer gameTime;
	
	public PlayView() {
		initializeGame();
	}
	
	private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
           processKeyPressed(e);
        }
	}   
	
	private void initializeGame() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//updateGame(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void processKeyPressed(KeyEvent event) {
		 int key = event.getKeyCode();

		 if (key == KeyEvent.VK_UP) {

		 }
		 else if (key == KeyEvent.VK_RIGHT) {

		 }
		 else if (key == KeyEvent.VK_DOWN) {

		 }
		 else if (key == KeyEvent.VK_LEFT) {

		 }
		 
		 repaint();
	}
	
	public void updateGame(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}
}
