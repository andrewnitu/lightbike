package main;

public class Line {
	int x1;
	int y1; 
	int x2;
	int y2;
	
	public Line(int newx1, int newy1, int newx2, int newy2) {
		x1 = newx1;
		y1 = newy1;
		x2 = newx2;
		y2 = newy2;
	}
	
	public int getx1() {
		return x1;
	}
	
	public int gety1() {
		return y1;
	}
	
	public int getx2() {
		return x2;
	}
	
	public int gety2() {
		return y2;
	}
}
