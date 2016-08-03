package lightbike;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInputMainMenu extends MouseAdapter {
	public void mouseMoved(MouseEvent e)
	{
		LightBike.mouseX = e.getX();
		LightBike.mouseY = e.getY();
		LightBike.updateButtons(true, false);
	}
	
	public void mouseReleased(MouseEvent e) {
		LightBike.mouseX = e.getX();
		LightBike.mouseY = e.getY();
		LightBike.updateButtons(false, true);
    }
}
