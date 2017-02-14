package main;

import java.awt.Color;

public class Player {
	Location location;
	Direction direction;
	Color color;
	boolean alive;
	int width;
	
	public Player(Location newLocation, Direction newDirection, Color newColor, boolean newAlive, int newWidth) {
		location = newLocation;
		direction = newDirection;
		color = newColor;
		alive = newAlive;
		width = newWidth;
	}
	
	public void setLocation(Location newLocation) {
		location = newLocation;
	}
	
	public void setDirection(Direction newDirection) {
		direction = newDirection;
	}
	
	public void setAlive(boolean newAlive) {
		alive = newAlive;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public int getWidth() {
		return width;
	}
}
