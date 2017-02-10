package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements ActionListener {
	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;

	private final int DELAY = 150;
	private final int MOVE_DISTANCE = 5;

	private int p1direction = 0;
	private Location p1location = new Location(640, 360);

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

		gameTime = new Timer(DELAY, this);
		gameTime.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateGame(g);

		Toolkit.getDefaultToolkit().sync();
	}

	public void processKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		if (key == KeyEvent.VK_UP) {

		} else if (key == KeyEvent.VK_RIGHT) {

		} else if (key == KeyEvent.VK_DOWN) {

		} else if (key == KeyEvent.VK_LEFT) {

		}
	}

	public void changeDirection(int direction) {
		// Need to make sure player doesn't go backwards!

		p1direction = direction;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// check collision with itself or other players

		move();
		repaint();
	}

	public void move() {

	}

	public void updateGame(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
	}
}
