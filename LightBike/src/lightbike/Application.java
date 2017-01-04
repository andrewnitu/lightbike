package lightbike;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Application extends JFrame {
	public static final int WINDOW_SIZE_X = 1280;
	public static final int WINDOW_SIZE_Y = 720;
	private static final String WINDOW_TITLE = "LightBike";
	
	public Application() {
		initialize();
	}
	
	private void initialize() {
		add(new LightBike());
		
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(false);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
