package main;

import java.awt.Color;
import java.awt.Font;

public abstract class Element {
	public Font font;
	public int xCoord1;
	public int yCoord1;
	public int xCoord2;
	public int yCoord2;
	public Color color;

	public int getxCoord1() {
		return xCoord1;
	}

	public int getxCoord2() {
		return xCoord2;
	}

	public int getyCoord1() {
		return yCoord1;
	}

	public int getyCoord2() {
		return yCoord2;
	}

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}
}
