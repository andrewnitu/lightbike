package main;

public class PlayerResult {
	int player;
	int ranking;
	
	public PlayerResult(int player, int ranking) {
		this.player = player;
		this.ranking = ranking;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getRanking() {
		return ranking;
	}
}
