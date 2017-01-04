package lightbike;

import java.awt.Color;
import java.awt.Font;

public class Button {
	public Font font;
	public int xCoord1;
	public int yCoord1;
	public int xCoord2;
	public int yCoord2;
	public String text;
	public boolean selected;
	public Color buttonColor;

	public Button (int x1, int y1, int x2, int y2, Color color, String btnText, Font btnFont) {
		xCoord1 = x1;
		yCoord1 = y1;
		xCoord2 = x2;
		yCoord2 = y2;
		buttonColor = color;
		text = btnText;
		font = btnFont;
	}
	
	public void setSelected (boolean isSelected) {
		selected = isSelected;
	}
	
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
		return buttonColor;
	}
	
	public String getText() {
		return text;
	}
	
	public Font getFont() {
		return font;
	}
}
