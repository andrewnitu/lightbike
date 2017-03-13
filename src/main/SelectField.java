package main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class SelectField extends Element {
	public int which;
	public ArrayList<String> values;

	private int valuesLength;

	public SelectField(int x1, int y1, int x2, int y2, Color fldColor, Font fldFont, ArrayList<String> fldValues,
			int fldWhich) {
		xCoord1 = x1;
		yCoord1 = y1;
		xCoord2 = x2;
		yCoord2 = y2;
		color = fldColor;
		font = fldFont;
		values = new ArrayList<String>(fldValues); //copy the values, don't reference
		which = fldWhich;

		valuesLength = values.size();
	}

	public int getWhich() {
		return which;
	}

	public String getText() {
		return values.get(which);
	}

	public void next() {
		if (which != valuesLength - 1) {
			which++;
		}
	}

	public void previous() {
		if (which != 0) {
			which--;
		}
	}
}
