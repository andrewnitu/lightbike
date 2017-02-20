package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.EventQueue;

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
	
	public static final boolean DEBUG = false;

	private static final String WINDOW_TITLE = "LightBike";

	public Application() {
		initialize();
	}

	private void initialize() {
		mainMenuView = new MainMenuView();
		settingsView = new SettingsView();
		playView = new PlayView();

		// Set the references to this instance so that the views can call the change methods below
		mainMenuView.setApp(this);
		settingsView.setApp(this);
		playView.setApp(this);

		cards.add(mainMenuView, "MainMenu");
		cards.add(settingsView, "Settings");
		cards.add(playView, "Play");

		setResizable(false);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		add(cards);
		pack();
	}

	public void swapSettings() {
		cardLayout.show(cards, "Settings");
	}

	public void swapPlay() {
		cardLayout.show(cards, "Play");
		playView.start();
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
