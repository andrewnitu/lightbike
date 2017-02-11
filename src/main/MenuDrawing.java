package main;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class MenuDrawing {
	public static void draw(Button e, Graphics2D g2d, boolean drawBorder) {
		int xCoord1 = e.getxCoord1();
		int xCoord2 = e.getxCoord2();
		int yCoord1 = e.getyCoord1();
		int yCoord2 = e.getyCoord2();

		String text = e.getText();
		Color buttonColor = e.getColor();

		// drawing the button
		g2d.setColor(buttonColor);
		g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);

		// drawing the button text
		g2d.setFont(e.getFont());
		FontMetrics metrics = g2d.getFontMetrics(e.getFont());
		int stringWidth = metrics.stringWidth(text);
		int stringHeight = metrics.getHeight();
		int stringAscent = metrics.getAscent();
		g2d.drawString(text, xCoord1 + (xCoord2 - xCoord1 - stringWidth) / 2,
				yCoord1 + (yCoord2 - yCoord1 - stringHeight) / 2 + stringAscent);

		// drawing (or erasing) the selection border
		if (!(drawBorder)) { // if not iterating on the current button, set the drawing colour to black
			g2d.setColor(Palette.BLACK);
		}
		g2d.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);
	}

	// TO BE COMPLETED, HOW DO WE DRAW A INTEGER FIELD?
	public static void draw(IntField e, Graphics2D g2d, boolean drawBorder) {
		int xCoord1 = e.getxCoord1();
		int xCoord2 = e.getxCoord2();
		int yCoord1 = e.getyCoord1();
		int yCoord2 = e.getyCoord2();

		int value = e.getValue();
		String valueText = value + "";

		Color buttonColor = e.getColor();

		// drawing the button
		g2d.setColor(buttonColor);
		g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);

		// drawing the button text
		g2d.setFont(e.getFont());
		FontMetrics metrics = g2d.getFontMetrics(e.getFont());
		int stringWidth = metrics.stringWidth(valueText);
		int stringHeight = metrics.getHeight();
		int stringAscent = metrics.getAscent();
		g2d.drawString(valueText, xCoord1 + (xCoord2 - xCoord1 - stringWidth) / 2,
				yCoord1 + (yCoord2 - yCoord1 - stringHeight) / 2 + stringAscent);

		// drawing (or erasing) the selection border
		if (!(drawBorder)) { // if not iterating on the current button, set the drawing colour to black
			g2d.setColor(Palette.BLACK);
		}
		g2d.drawRect(xCoord1 - 10, yCoord1 - 10, xCoord2 - xCoord1 + 20, yCoord2 - yCoord1 + 20);
	}
}
