package net.gedzis.memory.comparator;

import java.util.Comparator;

import net.gedzis.memory.model.PlayerScore;

public class TimeRushGamePlayerScoreComparator implements
		Comparator<PlayerScore> {

	public int compare(PlayerScore player1, PlayerScore player2) {
		if (player1.getTurns() < player2.getTurns()) {
			return 1;
		} else {
			return -1;
		}
	}

}
