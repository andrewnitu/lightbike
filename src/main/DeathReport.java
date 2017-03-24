package main;

import java.util.ArrayList;

public class DeathReport {
	ArrayList<Integer> players;
	int time;
	
	public DeathReport(ArrayList<Integer> newPlayers, int newTime) {
		players = newPlayers;
		time = newTime;
	}
	
	// Returns the players that died at this time.
	public ArrayList<Integer> getPlayers() {
		return players;
	}
	
	// Returns the time at which this set of players died.
	public int getTime() {
		return time;
	}
}
