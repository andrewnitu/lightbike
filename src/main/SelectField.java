package main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class SelectField extends Element {
	public int which;
	public ArrayList<String> values;

	private int valuesLength;

	public SelectField(int x1, int y1, int x2, int y2, Color color, Font font, ArrayList<String> values,
			int which) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.font = font;
		this.values = new ArrayList<String>(values); //copy the values, don't reference
		this.which = which;

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
