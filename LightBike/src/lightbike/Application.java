package lightbike;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Application extends JFrame {
	private static final String WINDOW_TITLE = "LightBike";
	
	public Application() {
		initialize();
	}
	
	private void initialize() {
		add(new LightBike());
		
		setResizable(false);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
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
