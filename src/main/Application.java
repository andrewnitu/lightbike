package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Application extends JFrame {
	// Reference http://stackoverflow.com/questions/6175899/how-to-change-card-layout-panels-from-another-panel

	private CardLayout cardLayout = new CardLayout();
	private JPanel cards = new JPanel(cardLayout);
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;

	MainMenuView mainMenuView;
	SettingsView settingsView;
	PlayView playView;
	GameOverView gameOverView;
	
	int players = 1;
	String size = "Small";
	String speed = "Brisk";
	
	public static final boolean DEBUG = true;

	private static final String WINDOW_TITLE = "LightBike";

	public Application() {
		initialize();
	}

	private void initialize() {
		mainMenuView = new MainMenuView();
		settingsView = new SettingsView();
		//playView = new PlayView();
		gameOverView = new GameOverView();
		
		// Set the references to this instance so that the views can call the change methods below
		mainMenuView.setApp(this);
		settingsView.setApp(this);
		//playView.setApp(this);
		gameOverView.setApp(this);

		cards.add(mainMenuView, "MainMenu");
		cards.add(settingsView, "Settings");
		//cards.add(playView, "Play");
		//cards.add(gameOverView, "GameOver");
		
		mainMenuView.start();
		settingsView.start();
		//playView.start();
		//gameOverView.start();

		setResizable(false);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		add(cards);
		pack();
	}
	
	public void swapMainMenu() {
		cardLayout.show(cards, "MainMenu");
		mainMenuView.resume();
	}

	public void swapSettings() {
		cardLayout.show(cards, "Settings");
		settingsView.resume();
	}

	public void swapPlay() {
		playView = new PlayView();
		playView.setApp(this);
		cards.add(playView, "Play");
		cardLayout.show(cards, "Play");
		
		if (DEBUG) {
			System.out.println(players + " players");
			System.out.println(size + " player width");
			System.out.println(speed + " speed");
		}
		
		playView.start();
	}
	
	public void swapGameOver(DeathReport playerDeaths) {
		gameOverView = new GameOverView();
		gameOverView.setApp(this);
		
		if (DEBUG) {
			for (int p = 0; p < players; p++) {
				//System.out.println("Player " + deads.get(p) + " took " + (players - p) + " place");
			}
		}
		
		cards.add(gameOverView, "GameOver");
		cardLayout.show(cards, "GameOver");
		gameOverView.start(playerDeaths);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Application a = new Application();
				a.setVisible(true);
			}
		});
	}
}
