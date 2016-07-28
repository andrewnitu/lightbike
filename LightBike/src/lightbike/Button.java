package lightbike;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.FontMetrics;

public class Button {
	public static final Font buttonFont = new Font ("Helvetica", Font.BOLD, 32);

	public Button (int x1, int y1, int x2, int y2, Color buttonColor, String text, Graphics2D g) {
		final int buttonWidth = x2 - x1; //width of the button for use in calculations later
		final int buttonHeight = y2 - y1; //height of the button for use in calculations later
		
		Font prevFont = g.getFont(); //save the previous drawing font
		Color prevColor = g.getColor(); //save the previous drawing color

		g.setFont(buttonFont); //set the new font for the button
		g.setColor(buttonColor); //set the color that the button will be

		g.drawRect(x1, y1, buttonWidth, buttonHeight); //draw the button outline

		FontMetrics metrics = g.getFontMetrics(buttonFont); //create metrics to measure the string
		
		final int stringWidth = metrics.stringWidth(text);
		final int stringHeight = metrics.getHeight();
		final int stringAscent = metrics.getAscent();
		
		g.drawString(text, x1 + (buttonWidth - stringWidth)/2, y1 + (buttonHeight - stringHeight)/2 + stringAscent); //complicated wizardry that finally centers the string vertically
		
		g.setFont(prevFont);
		g.setColor(prevColor);
	}
}
