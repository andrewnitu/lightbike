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

	private int delay;

	private int playerWidth = 0;

	private static final int SMALL_WIDTH = 7; //px
	private static final int MEDIUM_WIDTH = 15; //px
	private static final int LARGE_WIDTH = 23; //px

	private int oneSide;

	private Location tempLocation;

	// TODO: Initialize to 0s for readability
	private int[][] gameBoard = new int[WINDOW_WIDTH][WINDOW_HEIGHT];
	private ArrayList<ArrayList<Line>> playerLines = new ArrayList<ArrayList<Line>>();
	private ArrayList<ArrayList<Rectangle>> playerRectangles = new ArrayList<ArrayList<Rectangle>>();
	private ArrayList<Integer> playerLineCounts = new ArrayList<Integer>();
	private DeathReport playerDeaths = new DeathReport();

	private int currentLineCount = 0;

	private Application application;

	private Timer gameTime;
	private int timeElapsed;

	private int numPlayers = 2;
	private int numAlive;

	private Player p1, p2, p3, p4;
	private ArrayList<Player> players = new ArrayList<Player>();

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

	// unused
	public void resume() {
		requestFocus();

		gameTime = new Timer(delay, this);
		gameTime.start();
	}

	public void stop() {
		gameTime.stop();
	}

	private void initializeGame() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Palette.BLACK);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		numPlayers = application.players;
		numAlive = numPlayers;
		timeElapsed = 0;

		// Colours for different values selected
		switch (application.speed) {
		case "Slow":
			delay = 8;
			break;
		case "Medium":
			delay = 5;
			break;
		case "Fast":
			delay = 3;
			break;
		case "Impossible":
			delay = 1;
			break;
		default:
			delay = 5;
			break;
		}

		// Adjust player width
		if (application.size.equals("Small")) {
			playerWidth = SMALL_WIDTH;
		}
		else if (application.size.equals("Medium")) {
			playerWidth = MEDIUM_WIDTH;
		}
		else if (application.size.equals("Large")) {
			playerWidth = LARGE_WIDTH;
		}
		oneSide = ((playerWidth - 1) / 2);

		// Initialize the game arrays
		for (int p = 0; p < numPlayers; p++) {
			playerRectangles.add(new ArrayList<Rectangle>());
			playerLines.add(new ArrayList<Line>());
			playerLineCounts.add(0);
		}

		// Set up the players
		p1 = new Player(new Location(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 - 4 * playerWidth), Direction.UP,
				Palette.GREEN, true, playerWidth);
		players.add(p1);

		p2 = new Player(new Location(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 + 4 * playerWidth), Direction.DOWN,
				Palette.MAGENTA, true, playerWidth);
		players.add(p2);

		p3 = new Player(new Location(WINDOW_WIDTH / 2 + 4 * playerWidth, WINDOW_HEIGHT / 2), Direction.RIGHT,
				Palette.RED, true, playerWidth);
		players.add(p3);

		p4 = new Player(new Location(WINDOW_WIDTH / 2 - 4 * playerWidth, WINDOW_HEIGHT / 2), Direction.LEFT,
				Palette.BLUE, true, playerWidth);
		players.add(p4);

		for (int p = 0; p < numPlayers; p++) {
			// set the starting indices
			for (int i = -oneSide; i <= oneSide; i++) {
				for (int j = -oneSide; j <= oneSide; j++) {
					gameBoard[players.get(p).getLocation().getx() + i][players.get(p).getLocation().gety() + j] = p + 1;
				}
			}
			// create the starting block for the player
			playerRectangles.get(p).add(new Rectangle(players.get(p).getLocation().getx() - oneSide,
					players.get(p).getLocation().gety() - oneSide, players.get(p).getLocation().getx() + oneSide,
					players.get(p).getLocation().gety() + oneSide));
		}

		gameTime = new Timer(delay, this);
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
		ArrayList<Direction> currentDirections = new ArrayList<Direction>();

		for (int p = 0; p < numPlayers; p++) {
			currentDirections.add(players.get(p).getDirection());
		}

		// change the direction if the current direction is not along the same axis
		if (numPlayers >= 1) {
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
		}
		if (numPlayers >= 2) {
			if (p2.getDirection() != Direction.DOWN && p2.getDirection() != Direction.UP) {
				if (key == KeyEvent.VK_W) {
					p2.setDirection(Direction.UP);
				}
				else if (key == KeyEvent.VK_S) {
					p2.setDirection(Direction.DOWN);
				}
			}
			else if (p2.getDirection() != Direction.LEFT && p2.getDirection() != Direction.RIGHT) {
				if (key == KeyEvent.VK_D) {
					p2.setDirection(Direction.RIGHT);
				}
				else if (key == KeyEvent.VK_A) {
					p2.setDirection(Direction.LEFT);
				}
			}
		}
		if (numPlayers >= 3) {
			if (p3.getDirection() != Direction.DOWN && p3.getDirection() != Direction.UP) {
				if (key == KeyEvent.VK_I) {
					p3.setDirection(Direction.UP);
				}
				else if (key == KeyEvent.VK_K) {
					p3.setDirection(Direction.DOWN);
				}
			}
			else if (p3.getDirection() != Direction.LEFT && p3.getDirection() != Direction.RIGHT) {
				if (key == KeyEvent.VK_L) {
					p3.setDirection(Direction.RIGHT);
				}
				else if (key == KeyEvent.VK_J) {
					p3.setDirection(Direction.LEFT);
				}
			}
		}
		if (numPlayers >= 4) {
			if (p4.getDirection() != Direction.DOWN && p4.getDirection() != Direction.UP) {
				if (key == KeyEvent.VK_T) {
					p4.setDirection(Direction.UP);
				}
				else if (key == KeyEvent.VK_G) {
					p4.setDirection(Direction.DOWN);
				}
			}
			else if (p4.getDirection() != Direction.LEFT && p4.getDirection() != Direction.RIGHT) {
				if (key == KeyEvent.VK_H) {
					p4.setDirection(Direction.RIGHT);
				}
				else if (key == KeyEvent.VK_F) {
					p4.setDirection(Direction.LEFT);
				}
			}
		}

		// executed when player changes direction
		// convert individual lines to a big rectangle, change the player position, and update the corner indices
		for (int p = 0; p < numPlayers; p++) {
			if (players.get(p).getDirection() != currentDirections.get(p) && players.get(p).getAlive()) {
				if (currentDirections.get(p) == Direction.UP) {
					playerRectangles.get(p)
							.add(new Rectangle(players.get(p).getLocation().getx() - oneSide,
									players.get(p).getLocation().gety() - oneSide,
									players.get(p).getLocation().getx() + oneSide,
									players.get(p).getLocation().gety() - oneSide + playerLineCounts.get(p)));
				}
				else if (currentDirections.get(p) == Direction.RIGHT) {
					playerRectangles.get(p)
							.add(new Rectangle(players.get(p).getLocation().getx() + oneSide - playerLineCounts.get(p),
									players.get(p).getLocation().gety() - oneSide,
									players.get(p).getLocation().getx() + oneSide,
									players.get(p).getLocation().gety() + oneSide));
				}
				else if (currentDirections.get(p) == Direction.DOWN) {
					playerRectangles.get(p)
							.add(new Rectangle(players.get(p).getLocation().getx() - oneSide,
									players.get(p).getLocation().gety() + oneSide - playerLineCounts.get(p),
									players.get(p).getLocation().getx() + oneSide,
									players.get(p).getLocation().gety() + oneSide));
				}
				else if (currentDirections.get(p) == Direction.LEFT) {
					playerRectangles.get(p)
							.add(new Rectangle(players.get(p).getLocation().getx() - oneSide,
									players.get(p).getLocation().gety() - oneSide,
									players.get(p).getLocation().getx() - oneSide + playerLineCounts.get(p),
									players.get(p).getLocation().gety() + oneSide));
				}

				for (int i = -oneSide; i <= oneSide; i++) {
					for (int j = -oneSide; j <= oneSide; j++) {
						gameBoard[players.get(p).getLocation().getx() + i][players.get(p).getLocation().gety() + j] = p
								+ 1;
					}
				}

				playerLines.get(p).clear();
				playerLineCounts.set(p, 0);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: check collision with itself or other players
		move();
		repaint();
	}

	public boolean checkCollision(Location playerLoc, Direction dir) {
		if (dir == Direction.UP) {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[playerLoc.getx() + i][tempLocation.gety() - oneSide] != 0) {
					if (Application.DEBUG)
						System.out.println("Collision dir up!");
					return true;
				}
			}
		}
		else if (dir == Direction.RIGHT) {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[playerLoc.getx() + oneSide][playerLoc.gety() + i] != 0) {
					if (Application.DEBUG)
						System.out.println("Collision dir right!");
					return true;
				}
			}
		}
		else if (dir == Direction.DOWN) {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[playerLoc.getx() + i][tempLocation.gety() + oneSide] != 0) {
					if (Application.DEBUG)
						System.out.println("Collision dir down!");
					return true;
				}
			}
		}
		else if (dir == Direction.LEFT) {
			for (int i = -oneSide; i <= oneSide; i++) {
				if (gameBoard[playerLoc.getx() - oneSide][playerLoc.gety() + i] != 0) {
					if (Application.DEBUG)
						System.out.println("Collision dir left!");
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkOutOfBounds(Location loc) {
		if (loc.getx() - oneSide < 1 || loc.getx() + oneSide >= WINDOW_WIDTH - 1 || loc.gety() - oneSide < 1
				|| loc.gety() + oneSide >= WINDOW_HEIGHT - 1) {
			return true;
		}
		return false;
	}

	public void move() {
		timeElapsed++;
		if (numAlive == 1) {
			application.swapGameOver(playerDeaths);
			stop();
		}

		for (int p = 0; p < numPlayers; p++) {
			if (players.get(p).getAlive()) {
				tempLocation = players.get(p).getLocation();

				if (players.get(p).getDirection() == Direction.UP) {
					tempLocation.sety(tempLocation.gety() - 1);
				}
				else if (players.get(p).getDirection() == Direction.RIGHT) {
					tempLocation.setx(tempLocation.getx() + 1);
				}
				else if (players.get(p).getDirection() == Direction.DOWN) {
					tempLocation.sety(tempLocation.gety() + 1);
				}
				else if (players.get(p).getDirection() == Direction.LEFT) {
					tempLocation.setx(tempLocation.getx() - 1);
				}
				players.get(p).setLocation(tempLocation); // location is moved by one pixel in some direction

				if (checkCollision(players.get(p).getLocation(), players.get(p).getDirection())
						|| checkOutOfBounds(players.get(p).getLocation())) {
					players.get(p).setAlive(false);
					playerDeaths.addDeath(p + 1, timeElapsed);
					numAlive--;
				}
				else {
					if (players.get(p).getDirection() == Direction.UP) {
						for (int i = -oneSide; i <= oneSide; i++) {
							gameBoard[tempLocation.getx() + i][tempLocation.gety() - oneSide] = 1;
						}
						playerLines.get(p)
								.add(new Line(players.get(p).getLocation().getx() - oneSide,
										players.get(p).getLocation().gety() - oneSide,
										players.get(p).getLocation().getx() + oneSide,
										players.get(p).getLocation().gety() - oneSide));
					}
					else if (players.get(p).getDirection() == Direction.DOWN) {
						for (int i = -oneSide; i <= oneSide; i++) {
							gameBoard[tempLocation.getx() + i][tempLocation.gety() + oneSide] = 1;
						}
						playerLines.get(p)
								.add(new Line(players.get(p).getLocation().getx() - oneSide,
										players.get(p).getLocation().gety() + oneSide,
										players.get(p).getLocation().getx() + oneSide,
										players.get(p).getLocation().gety() + oneSide));
					}
					else if (players.get(p).getDirection() == Direction.LEFT) {
						for (int i = -oneSide; i <= oneSide; i++) {
							gameBoard[tempLocation.getx() - oneSide][tempLocation.gety() + i] = 1;
						}
						playerLines.get(p)
								.add(new Line(players.get(p).getLocation().getx() - oneSide,
										players.get(p).getLocation().gety() - oneSide,
										players.get(p).getLocation().getx() - oneSide,
										players.get(p).getLocation().gety() + oneSide));
					}
					else if (players.get(p).getDirection() == Direction.RIGHT) {
						for (int i = -oneSide; i <= oneSide; i++) {
							gameBoard[tempLocation.getx() + oneSide][tempLocation.gety() + i] = 1;
						}
						playerLines.get(p)
								.add(new Line(players.get(p).getLocation().getx() + oneSide,
										players.get(p).getLocation().gety() - oneSide,
										players.get(p).getLocation().getx() + oneSide,
										players.get(p).getLocation().gety() + oneSide));
					}
					playerLineCounts.set(p, playerLineCounts.get(p) + 1);
				}
			}
		}
	}

	public void updateGame(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		for (int p = 0; p < numPlayers; p++) {
			g.setColor(players.get(p).getColor());

			// draw all the lines
			for (int i = 0; i < playerLines.get(p).size(); i++) {
				g.drawLine(playerLines.get(p).get(i).getx1(), playerLines.get(p).get(i).gety1(),
						playerLines.get(p).get(i).getx2(), playerLines.get(p).get(i).gety2());
			}

			// draw all the rectangles
			for (int i = 0; i < playerRectangles.get(p).size(); i++) {
				g.fillRect(playerRectangles.get(p).get(i).getx1(), playerRectangles.get(p).get(i).gety1(),
						playerRectangles.get(p).get(i).getx2() + 1 - playerRectangles.get(p).get(i).getx1(),
						playerRectangles.get(p).get(i).gety2() + 1 - playerRectangles.get(p).get(i).gety1());
			}
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
