package main;

import java.awt.Color;
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

	private final int DELAY = 10;

	private Direction p1direction = Direction.UP;
	private Location p1location = new Location(640, 360);

	// TODO: Initialize to 0s for readability
	private int[][] gameBoard = new int[WINDOW_WIDTH][WINDOW_HEIGHT];

	private Color p1color = Palette.LIME_GREEN;

	private Application application;

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
			System.out.println("UP DETECTED");
			changeDirection(Direction.UP);
		}
		else if (key == KeyEvent.VK_RIGHT) {
			changeDirection(Direction.RIGHT);
		}
		else if (key == KeyEvent.VK_DOWN) {
			changeDirection(Direction.DOWN);
		}
		else if (key == KeyEvent.VK_LEFT) {
			changeDirection(Direction.LEFT);
		}
	}

	public void changeDirection(Direction direction) {
		// TODO: Make sure player doesn't go backwards

		p1direction = direction;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: check collision with itself or other players

		move();
		repaint();
	}

	public void move() {
		if (p1direction == Direction.UP) {
			p1location.sety(p1location.gety() - 1);
		}
		else if (p1direction == Direction.RIGHT) {
			p1location.setx(p1location.getx() + 1);
		}
		else if (p1direction == Direction.DOWN) {
			p1location.sety(p1location.gety() + 1);
		}
		else if (p1direction == Direction.LEFT) {
			p1location.setx(p1location.getx() - 1);
		}

		gameBoard[p1location.getx()][p1location.gety()] = 1;
	}

	public void updateGame(Graphics g) {
		// TODO: Implement drawing here

		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[1].length; y++) {
				if (gameBoard[x][y] != 0) {
					if (gameBoard[x][y] == 1) {
						g.setColor(p1color);
						g.drawLine(x + 1, y + 1, x + 1, y + 1);
					}
				}
			}
		}
	}

	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
