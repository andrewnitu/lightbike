package main;

import java.awt.Color;
import java.awt.Font;

public class Button extends Element {
	public String text;

	public Button(int x1, int y1, int x2, int y2, Color color, String text, Font font) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.text = text;
		this.font = font;
	}

	public String getText() {
		return text;
	}
}
