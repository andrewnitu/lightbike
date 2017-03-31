package main;

import java.awt.Color;
import java.awt.Font;

public abstract class Element {
	public Font font;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public Color color;

	public int getxCoord1() {
		return x1;
	}

	public int getxCoord2() {
		return x2;
	}

	public int getyCoord1() {
		return y1;
	}

	public int getyCoord2() {
		return y2;
	}

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}
}
