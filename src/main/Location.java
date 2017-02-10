package main;

public class Location {
	private int locationx;
	private int locationy;

	public Location(int x, int y) {
		locationx = x;
		locationy = y;
	}

	public int getx() {
		return locationx;
	}

	public int gety() {
		return locationy;
	}

	public void setx(int x) {
		locationx = x;
	}

	public void sety(int y) {
		locationy = y;
	}
}
