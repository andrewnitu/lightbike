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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements ActionListener {
	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;

	private final int DELAY = 10;

	private int p1offset = 0;

	private final int playerWidth = 151;
	private final int offsetThreshold = playerWidth;
	private Direction p1restricted;

	Player p1 = new Player(new Location(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2), Direction.UP, Palette.LIME_GREEN, true,
			playerWidth);

	private Location tempLocation;

	// TODO: Initialize to 0s for readability
	private int[][] gameBoard = new int[WINDOW_WIDTH][WINDOW_HEIGHT];
	private ArrayList<Line> lines = new ArrayList<Line>();
	private int lineCount = 0;

	private Application application;

	private Timer gameTime;

	public PlayView() {
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			processKeyPressed(e);
		}
	}

	public void start() {
		initializeGame();
		requestFocus();
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
		Direction ogdir = p1.getDirection();

		//if (p1offset >= offsetThreshold) {
			if (p1.getDirection() != Direction.DOWN && p1.getDirection() != Direction.UP) {
				if (key == KeyEvent.VK_UP) {
					p1.setDirection(Direction.UP);
				}
				else if (key == KeyEvent.VK_DOWN) {
					p1.setDirection(Direction.DOWN);
				}
			}
			else if (p1.getDirection() != Direction.LEFT && p1.getDirection() != Direction.RIGHT) {
				if (key == KeyEvent.VK_RIGHT) {
					p1.setDirection(Direction.RIGHT);
				}
				else if (key == KeyEvent.VK_LEFT) {
					p1.setDirection(Direction.LEFT);
				}
			}
		//}

		if (p1.getDirection() != ogdir) {
			for (int i = -((playerWidth - 1) / 2); i <= ((playerWidth - 1) / 2); i++) {
				for (int j = -((playerWidth - 1) / 2); j <= ((playerWidth - 1) / 2); j++) {
					gameBoard[p1.getLocation().getx() + i][p1.getLocation().gety() + j] = 1;
				}
				lines.add(new Line(p1.getLocation().getx() - ((playerWidth - 1) / 2), p1.getLocation().gety() + i, p1.getLocation().getx() + ((playerWidth - 1) / 2), p1.getLocation().gety() + i));
			}
		}

		lineCount++;
		p1offset = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: check collision with itself or other players
		move();
		repaint();
	}

	public void move() {
		p1offset++;

		tempLocation = p1.getLocation();

		if (p1.getDirection() == Direction.UP) {
			tempLocation.sety(tempLocation.gety() - 1);
			p1.setLocation(tempLocation);
		}
		else if (p1.getDirection() == Direction.RIGHT) {
			tempLocation.setx(tempLocation.getx() + 1);
			p1.setLocation(tempLocation);
		}
		else if (p1.getDirection() == Direction.DOWN) {
			tempLocation.sety(tempLocation.gety() + 1);
			p1.setLocation(tempLocation);
		}
		else if (p1.getDirection() == Direction.LEFT) {
			tempLocation.setx(tempLocation.getx() - 1);
			p1.setLocation(tempLocation);
		}

		if (p1.getDirection() == Direction.UP || p1.getDirection() == Direction.DOWN) {
			for (int i = -((playerWidth - 1) / 2); i <= ((playerWidth - 1) / 2); i++) {
				gameBoard[tempLocation.getx() + i][tempLocation.gety()] = 1;
			}
			lines.add(new Line(p1.getLocation().getx() - ((playerWidth - 1) / 2), p1.getLocation().gety(), p1.getLocation().getx() + ((playerWidth - 1) / 2), p1.getLocation().gety()));
		}
		else {
			for (int i = -((playerWidth - 1) / 2); i <= ((playerWidth - 1) / 2); i++) {
				gameBoard[tempLocation.getx()][tempLocation.gety() + i] = 1;
			}
			lines.add(new Line(p1.getLocation().getx(), p1.getLocation().gety() - ((playerWidth - 1) / 2), p1.getLocation().getx(), p1.getLocation().gety() + ((playerWidth - 1) / 2)));
		}
	}

	public void updateGame(Graphics g) {
		// TODO: Implement drawing here

		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		/*for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[1].length; y++) {
				if (gameBoard[x][y] != 0) {
					if (gameBoard[x][y] == 1) {
						g.setColor(p1.getColor());
						g.drawLine(x + 1, y + 1, x + 1, y + 1);
					}
				}
			}
		}*/
		
		for (int i = 0; i < lines.size(); i++) {
			g.drawLine(lines.get(i).getx1(), lines.get(i).gety1(), lines.get(i).getx2(), lines.get(i).gety2());
		}
	}

	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
