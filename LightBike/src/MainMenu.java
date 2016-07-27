import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings({"unused","serial"})

public class MainMenu extends JPanel implements ActionListener {

	//Game window will be 720p
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static boolean inMenu = 1;

	public MainMenu() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}
	
	public void mainMenu(Graphics g) {
		String button1 = "Start";
		String button2 = "Settings";
		String button3 = "Quit";
		
		Font menuFont = new Font("Helvetica", Font.BOLD, 18);
		
		g.setFont(menuFont);
		g.setColor(Color.green);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
	
	private void doDrawing(Graphics g) {
		if (inGame) {
			
		}
		else {
			
		}
	}
	
	private void startGame() {
		
	}

}
