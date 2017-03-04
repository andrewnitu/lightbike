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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements ActionListener {
	private final int WINDOW_WIDTH = Application.WINDOW_WIDTH;
	private final int WINDOW_HEIGHT = Application.WINDOW_HEIGHT;

	private final int DELAY = 10;

	private int p1offset = 0;

	private final int playerWidth = 5;
	private final int oneSide = ((playerWidth - 1) / 2);
	
	private final int offsetThreshold = playerWidth;

	Player p1 = new Player(new Location(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 - oneSide), Direction.UP, Palette.LIME_GREEN, true,
			playerWidth);

	private Location tempLocation;

	// TODO: Initialize to 0s for readability
	private int[][] gameBoard = new int[WINDOW_WIDTH][WINDOW_HEIGHT];
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private int currentLineCount = 0;

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
	}
	
	public void resume() {
		requestFocus();
		
		gameTime = new Timer(DELAY, this);
		gameTime.start();
	}

	private void initializeGame() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		// set the starting indices
		for (int i = -oneSide; i <= oneSide; i++) {
			for (int j = -oneSide; j <= oneSide; j++) {
				gameBoard[p1.getLocation().getx() + i][p1.getLocation().gety() + j] = 1;
			}
		}
		
		// create the starting block for the player
		rectangles.add(new Rectangle(p1.getLocation().getx() - oneSide, p1.getLocation().gety(),
						p1.getLocation().getx() + oneSide, p1.getLocation().gety() + oneSide * 2));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		updateGame(g);

		Toolkit.getDefaultToolkit().sync();
	}

	public void processKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		Direction currentDirection = p1.getDirection();

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

		// executed when player changes direction
		// convert individual lines to a big rectangle, change the player position, and update the corner indices
		if (p1.getDirection() != currentDirection) {
			if (currentDirection == Direction.UP) {
				rectangles.add(new Rectangle(p1.getLocation().getx() - oneSide, p1.getLocation().gety(),
						p1.getLocation().getx() + oneSide, p1.getLocation().gety() + currentLineCount));
				p1.setLocation(new Location(p1.getLocation().getx(), p1.getLocation().gety() + oneSide));
			}
			else if (currentDirection == Direction.RIGHT) {
				rectangles.add(new Rectangle(p1.getLocation().getx() - currentLineCount, p1.getLocation().gety() - oneSide,
						p1.getLocation().getx(), p1.getLocation().gety() + oneSide));
				p1.setLocation(new Location(p1.getLocation().getx() - oneSide, p1.getLocation().gety()));
			}
			else if (currentDirection == Direction.DOWN) {
				rectangles.add(new Rectangle(p1.getLocation().getx() - oneSide, p1.getLocation().gety() - currentLineCount,
						p1.getLocation().getx() + oneSide, p1.getLocation().gety()));
				p1.setLocation(new Location(p1.getLocation().getx(), p1.getLocation().gety() - oneSide));
			}
			else if (currentDirection == Direction.LEFT) {
				rectangles.add(new Rectangle(p1.getLocation().getx(), p1.getLocation().gety() - oneSide,
						p1.getLocation().getx() + currentLineCount, p1.getLocation().gety() + oneSide));
				p1.setLocation(new Location(p1.getLocation().getx() + oneSide, p1.getLocation().gety()));
			}
			
			for (int i = -oneSide; i <= oneSide; i++) {
				for (int j = -oneSide; j <= oneSide; j++) {
					gameBoard[p1.getLocation().getx() + i][p1.getLocation().gety() + j] = 1;
				}
			}
			
			if (p1.getDirection() == Direction.UP) {
				p1.setLocation(new Location(p1.getLocation().getx(), p1.getLocation().gety() - oneSide));
			}
			else if (p1.getDirection() == Direction.RIGHT) {
				p1.setLocation(new Location(p1.getLocation().getx() + oneSide, p1.getLocation().gety()));
			}
			else if (p1.getDirection() == Direction.DOWN) {
				p1.setLocation(new Location(p1.getLocation().getx(), p1.getLocation().gety() + oneSide));
			}
			else if (p1.getDirection() == Direction.LEFT) {
				p1.setLocation(new Location(p1.getLocation().getx() - oneSide, p1.getLocation().gety()));
			}

			lines.clear();
			currentLineCount = 0;
		}

		p1offset = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: check collision with itself or other players
		move();
		repaint();
	}
	
	public void checkCollision(Location loc, Direction dir) {
		if (dir == Direction.UP || dir == Direction.DOWN) {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[loc.getx() + i][tempLocation.gety()] != 0) {
					if (Application.DEBUG) System.out.println("Collision vertical!");
					break;
				}
			}
		}
		else {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[loc.getx()][loc.gety() + i] != 0) {
					if (Application.DEBUG) System.out.println("Collision horizontal!");
					break;
				}
			}
		}
	}

	public void move() {
		p1offset++;
		
		tempLocation = p1.getLocation();

		if (p1.getDirection() == Direction.UP) {
			tempLocation.sety(tempLocation.gety() - 1);
		}
		else if (p1.getDirection() == Direction.RIGHT) {
			tempLocation.setx(tempLocation.getx() + 1);
		}
		else if (p1.getDirection() == Direction.DOWN) {
			tempLocation.sety(tempLocation.gety() + 1);
		}
		else if (p1.getDirection() == Direction.LEFT) {
			tempLocation.setx(tempLocation.getx() - 1);
		}
		p1.setLocation(tempLocation); // location is moved by one pixel in some direction
		
		checkCollision(p1.getLocation(), p1.getDirection());

		if (p1.getDirection() == Direction.UP || p1.getDirection() == Direction.DOWN) {
			for (int i = -oneSide; i <= oneSide; i++) {
				gameBoard[tempLocation.getx() + i][tempLocation.gety()] = 1;
			}
			lines.add(new Line(p1.getLocation().getx() - oneSide, p1.getLocation().gety(),
					p1.getLocation().getx() + oneSide, p1.getLocation().gety()));
		}
		else {
			for (int i = -oneSide; i <= oneSide; i++) {
				gameBoard[tempLocation.getx()][tempLocation.gety() + i] = 1;
			}
			lines.add(new Line(p1.getLocation().getx(), p1.getLocation().gety() - oneSide, p1.getLocation().getx(),
					p1.getLocation().gety() + oneSide));
		}
		currentLineCount++;
	}

	public void updateGame(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		g.setColor(p1.getColor());

		// draw all the lines
		for (int i = 0; i < lines.size(); i++) {
			g.drawLine(lines.get(i).getx1(), lines.get(i).gety1(), lines.get(i).getx2(), lines.get(i).gety2());
		}

		// draw all the rectangles
		for (int i = 0; i < rectangles.size(); i++) {
			g.fillRect(rectangles.get(i).getx1(), rectangles.get(i).gety1(),
					rectangles.get(i).getx2() + 1 - rectangles.get(i).getx1(),
					rectangles.get(i).gety2() + 1 - rectangles.get(i).gety1());
		}

		if (Application.DEBUG) {
			g.setColor(Palette.RED);
			g.drawLine(p1.getLocation().getx(), p1.getLocation().gety(), p1.getLocation().getx(),
					p1.getLocation().gety());
		}
	}

	// to be called from Application.java
	public void setApp(Application app) {
		this.application = app;
	}
}
