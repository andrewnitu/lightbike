import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")

public class LightBike extends JFrame {
	public static final String WINDOW_TITLE = "Java LightBike";

	public LightBike() {
		add(new MainMenu());

		setResizable(false);
		pack();

		setTitle(WINDOW_TITLE);
		setIgnoreRepaint(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame ex = new LightBike();
				ex.setVisible(true);
			}
		});
	}
}
