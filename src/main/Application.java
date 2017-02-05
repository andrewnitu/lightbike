package lightbike;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Application extends JFrame {
	// Reference http://stackoverflow.com/questions/6175899/how-to-change-card-layout-panels-from-another-panel
	
	private CardLayout cardLayout = new CardLayout();
	private JPanel cards = new JPanel(cardLayout);
	
	private static final String WINDOW_TITLE = "LightBike";
	
	public Application() {
		initialize();
	}
	
	private void initialize() {
		MainMenuView mainMenuView = new MainMenuView();
		SettingsView settingsView = new SettingsView();
		
		// Set the references to this instance so that the views can call the change methods below
		mainMenuView.setApp(this);
		settingsView.setApp(this);
		
		cards.add(mainMenuView, "MainMenu");
		cards.add(settingsView, "Settings");
		
		setResizable(false);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(cards);
		pack();
	}
	
	public void swapView(String which) {
		cardLayout.show(cards, which);
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
