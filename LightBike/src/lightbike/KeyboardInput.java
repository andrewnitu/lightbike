package lightbike;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
	public void keyPressed (KeyEvent e) {
		System.out.println("Key typed: " + e.getKeyCode());
	}
}
