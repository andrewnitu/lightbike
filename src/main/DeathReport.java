package main;

import java.util.ArrayList;

public class DeathReport {
	ArrayList<Death> deaths;
	
	public DeathReport() {
		deaths = new ArrayList<Death>();
	}
	
	public void addDeath(int player, int time) {
		deaths.add(new Death(player, time));
	}
	
	ArrayList<Integer> processDeaths() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		return results;
	}
}
