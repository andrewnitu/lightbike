package main;

public class Death {
	int player;
	int time;
	
	public Death(int newPlayer, int newTime) {
		player = newPlayer;
		time = newTime;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getTime() {
		return time;
	}
}
