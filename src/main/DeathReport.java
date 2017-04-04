package main;

import java.util.ArrayList;
import java.util.Collections;

public class DeathReport {
	ArrayList<Death> deaths;

	public DeathReport() {
		deaths = new ArrayList<Death>();
	}

	public void addDeath(int player, int time) {
		deaths.add(new Death(player, time));
	}

	ArrayList<PlayerResult> processDeaths() {
		int ranking = 1;
		Collections.reverse(deaths);
		
		int prevTime = deaths.get(0).getTime();
		ArrayList<PlayerResult> results = new ArrayList<PlayerResult>();

		for (int i = 0; i < deaths.size(); i++) {
			System.out.println("Player " + deaths.get(i).getPlayer() + " TIME " + deaths.get(i).getTime());
		}

		for (int i = 0; i < deaths.size(); i++) {
			if (deaths.get(i).getTime() == prevTime) {
				results.add(new PlayerResult(deaths.get(i).getPlayer(), ranking));
			}
			else {
				prevTime = deaths.get(i).getTime();
				ranking++;
				results.add(new PlayerResult(deaths.get(i).getPlayer(), ranking));
			}
		}

		return results;
	}
}
