package lightbike;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			LightBike.updateButtons(0);
		else
			LightBike.updateButtons(1);	
	}
}