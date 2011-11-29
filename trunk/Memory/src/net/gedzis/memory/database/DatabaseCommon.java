package net.gedzis.memory.database;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.model.PlayerScore;
import android.database.Cursor;

public class DatabaseCommon {
	public static List<PlayerScore> getCardGamePlayerScoreList(Cursor c) {
		List<PlayerScore> scores = new ArrayList<PlayerScore>();
		if (c.moveToFirst()) {
			do {
				String name = c.getString(c.getColumnIndex("name"));
				String table = c.getString(c.getColumnIndex("tableid"));
				int turns = c.getInt(c.getColumnIndex("turns"));
				long time = c.getLong(c.getColumnIndex("time"));
				scores.add(new PlayerScore(name, turns, time, table));
			} while (c.moveToNext());
		}
		return scores;
	}

	public static List<PlayerScore> getTimeRushGamePlayerScoreList(Cursor c) {
		List<PlayerScore> scores = new ArrayList<PlayerScore>();
		if (c.moveToFirst()) {
			do {
				String name = c.getString(c.getColumnIndex("name"));
				int turns = c.getInt(c.getColumnIndex("correct"));
				scores.add(new PlayerScore(name, turns, 0, null));
			} while (c.moveToNext());
		}
		return scores;
	}
}
