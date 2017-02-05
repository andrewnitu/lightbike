package lightbike;

import java.awt.Color;
import java.awt.Font;

public class IntField extends Element {
	public int value;

	public IntField (int x1, int y1, int x2, int y2, Color fldColor, String fldText, Font fldFont, int fldValue) {
		xCoord1 = x1;
		yCoord1 = y1;
		xCoord2 = x2;
		yCoord2 = y2;
		color = fldColor;
		font = fldFont;
	}
	
	public int getValue() {
		return value;
	}
}
