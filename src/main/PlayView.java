package main;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayView extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//updateGame();
		
		Toolkit.getDefaultToolkit().sync();
	}
}
