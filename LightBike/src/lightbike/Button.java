package lightbike;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.FontMetrics;

public class Button {
	public static final Font buttonFont = new Font ("Helvetica", Font.BOLD, 32);
	public int xCoord1 = 0;
	public int yCoord1 = 0;
	public int xCoord2 = 0;
	public int yCoord2 = 0;
	public int state = 0;
	public Color buttonColor;
	Graphics2D graphics;

	public Button (int x1, int y1, int x2, int y2, Color color, String text, Graphics2D g) {
		state = 0;
		buttonColor = color;
		
		final int buttonWidth = x2 - x1; //width of the button for use in calculations later
		final int buttonHeight = y2 - y1; //height of the button for use in calculations later
		
		xCoord1 = x1; //store the coordinates in global variables so they can be checked against in checkMouseInbounds()
		yCoord1 = y1;
		xCoord2 = x2;
		yCoord2 = y2;
		graphics = g;
		
		Font prevFont = graphics.getFont(); //save the previous drawing font
		Color prevColor = graphics.getColor(); //save the previous drawing color

		graphics.setFont(buttonFont); //set the new font for the button
		graphics.setColor(buttonColor); //set the color that the button will be

		graphics.drawRect(x1, y1, buttonWidth, buttonHeight); //draw the button outline

		FontMetrics metrics = g.getFontMetrics(buttonFont); //create metrics to measure the string
		
		final int stringWidth = metrics.stringWidth(text);
		final int stringHeight = metrics.getHeight();
		final int stringAscent = metrics.getAscent();
		
		graphics.drawString(text, x1 + (buttonWidth - stringWidth)/2, y1 + (buttonHeight - stringHeight)/2 + stringAscent); //complicated wizardry that finally centers the string vertically
		
		graphics.setFont(prevFont);
		graphics.setColor(prevColor);
	}
	
	public boolean checkMouseInBounds() {
		if (LightBike.mouseX >= xCoord1 && LightBike.mouseX <= xCoord2 && LightBike.mouseY >= yCoord1 && LightBike.mouseY <= yCoord2) {
			return true;
		}
		return false;
	}
	
	public void change(int toState) {
		switch (toState) {
		case 0: state = toState;
		graphics.setColor(LightBike.BLACK);
		graphics.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);
		case 1: state = toState;
		graphics.setColor(buttonColor);
		graphics.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);	
		}
	}
}
