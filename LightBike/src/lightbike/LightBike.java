package lightbike;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")

// Main class
public class LightBike extends Canvas {
	public static final String WINDOW_TITLE = "Java LightBike";
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final Color LIME_GREEN = new Color(0,255,0);
	private BufferStrategy bufferStrategy;
	
	public LightBike() {
		JFrame container = new JFrame(WINDOW_TITLE);
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		panel.setLayout(null);
		
		setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addKeyListener(new KeyInputHandler());
		
		requestFocus();
		
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		
		mainMenu();
	}
	
	private class KeyInputHandler extends KeyAdapter {
		
	}
	
	public void mainMenu(){
		final int TITLE_RATIO = 4;
		
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		Font titleFont = new Font("Helvetica",Font.BOLD,128);
		FontMetrics titleMetrics = g.getFontMetrics(titleFont);
		g.setFont(titleFont);
		g.setColor(LIME_GREEN);
		
		g.drawString("LightBike", (WINDOW_WIDTH - titleMetrics.stringWidth("LightBike"))/2, WINDOW_HEIGHT/TITLE_RATIO);
		
		Button test = new Button(20, 20, 100, 100, Color.GREEN, "Test", g);
		
		g.dispose();
		bufferStrategy.show();
	}

	public static void main(String[] args) {
		new LightBike();
	}
}
