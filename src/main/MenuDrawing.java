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

		// drawing the selection border
		if (drawBorder) {
			g2d.setColor(Palette.RED);
			g2d.drawRect(xCoord1 - 1, yCoord1 - 1, xCoord2 - xCoord1 + 2, yCoord2 - yCoord1 + 2);
			g2d.drawRect(xCoord1 - 2, yCoord1 - 2, xCoord2 - xCoord1 + 4, yCoord2 - yCoord1 + 4);
		}
	}

	// TODO: Complete method, how do we draw an integer field?
	public static void draw(IntField e, Graphics2D g2d, boolean drawBorder) {
		int xCoord1 = e.getxCoord1();
		int xCoord2 = e.getxCoord2();
		int yCoord1 = e.getyCoord1();
		int yCoord2 = e.getyCoord2();
		Color valColor = Palette.BLUE;

		int value = e.getValue();
		String valueText = value + "";

		Color buttonColor = e.getColor();

		// drawing the button
		g2d.setColor(buttonColor);
		g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);

		// drawing the button text
		g2d.setFont(e.getFont());
		
		switch (value) {
			case 1: valColor = Palette.GREEN;
				break;
			case 2: valColor = Palette.BLUE;
				break;
			case 3: valColor = Palette.MAGENTA;
				break;
			case 4: valColor = Palette.RED;
				break;
			default: break;
		}
		g2d.setColor(valColor);
		FontMetrics metrics = g2d.getFontMetrics(e.getFont());
		int stringWidth = metrics.stringWidth(valueText);
		int stringHeight = metrics.getHeight();
		int stringAscent = metrics.getAscent();
		g2d.drawString(valueText, xCoord1 + (xCoord2 - xCoord1 - stringWidth) / 2,
				yCoord1 + (yCoord2 - yCoord1 - stringHeight) / 2 + stringAscent);

		// drawing the selection border
		if (drawBorder) {
			g2d.setColor(Palette.RED);
			g2d.drawRect(xCoord1 - 1, yCoord1 - 1, xCoord2 - xCoord1 + 1, yCoord2 - yCoord1 + 1);
			g2d.drawRect(xCoord1 - 2, yCoord1 - 2, xCoord2 - xCoord1 + 4, yCoord2 - yCoord1 + 4);
		}
	}
	
	public static void draw(SelectField e, Graphics2D g2d, boolean drawBorder) {
		int xCoord1 = e.getxCoord1();
		int xCoord2 = e.getxCoord2();
		int yCoord1 = e.getyCoord1();
		int yCoord2 = e.getyCoord2();
		Color valColor = Palette.BLUE;

		String text = e.getText();

		Color buttonColor = e.getColor();

		// drawing the button
		g2d.setColor(buttonColor);
		g2d.drawRect(xCoord1, yCoord1, xCoord2 - xCoord1, yCoord2 - yCoord1);

		// drawing the button text
		g2d.setFont(e.getFont());
		switch (text) {
			case "Slow":
			case "Small": valColor = Palette.GREEN;
				break;
			case "Medium": valColor = Palette.BLUE;
				break;
			case "Fast": valColor = Palette.MAGENTA;
				break;
			case "Impossible":
			case "Large": valColor = Palette.RED;
			default: break;
		}
		g2d.setColor(valColor);
		FontMetrics metrics = g2d.getFontMetrics(e.getFont());
		int stringWidth = metrics.stringWidth(text);
		int stringHeight = metrics.getHeight();
		int stringAscent = metrics.getAscent();
		g2d.drawString(text, xCoord1 + (xCoord2 - xCoord1 - stringWidth) / 2,
				yCoord1 + (yCoord2 - yCoord1 - stringHeight) / 2 + stringAscent);

		// drawing the selection border
		if (drawBorder) {
			g2d.setColor(Palette.RED);
			g2d.drawRect(xCoord1 - 1, yCoord1, xCoord2 - xCoord1 + 2, yCoord2 - yCoord1 + 2);
			g2d.drawRect(xCoord1 - 2, yCoord1 - 2, xCoord2 - xCoord1 + 4, yCoord2 - yCoord1 + 4);
		}
	}
}
