package main;

import java.awt.Color;
import java.awt.Font;

public class IntField extends Element {
	public int value;
	public int min;
	public int max;

	public IntField(int x1, int y1, int x2, int y2, Color color, Font font, int value,
			int min, int max) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.font = font;
		this.value = value;
		this.min = min;
		this.max = max;
	}

	public int getValue() {
		return value;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void changeValue(int delta) {
		int newValue = value + delta;
		
		setValue(newValue);
	}
	
	public void setValue(int newValue) {
		if (newValue >= min && newValue <= max) {
			value = newValue;
		}
	}
}
