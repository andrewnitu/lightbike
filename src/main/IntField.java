package main;

import java.awt.Color;
import java.awt.Font;

public class IntField extends Element {
	public int value;
	public int min;
	public int max;

	public IntField(int x1, int y1, int x2, int y2, Color fldColor, String fldText, Font fldFont, int fldValue,
			int fldMin, int fldMax) {
		xCoord1 = x1;
		yCoord1 = y1;
		xCoord2 = x2;
		yCoord2 = y2;
		color = fldColor;
		font = fldFont;
		value = fldValue;
		min = fldMin;
		max = fldMax;
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
