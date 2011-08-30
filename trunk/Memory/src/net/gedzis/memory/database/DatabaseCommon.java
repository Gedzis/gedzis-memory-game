package net.gedzis.memory.database;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.model.PlayerScore;
import android.database.Cursor;

public class DatabaseCommon {
	public static List<PlayerScore> getPlayerScoreList(Cursor c) {
		List<PlayerScore> scores = new ArrayList<PlayerScore>();
		if (c.moveToFirst()) {
			do {
				String name = c.getString(c.getColumnIndex("name"));
				String table = c.getString(c.getColumnIndex("table"));
				int turns = c.getInt(c.getColumnIndex("turns"));
				int time = c.getInt(c.getColumnIndex("time"));
				scores.add(new PlayerScore(name, turns, time, table));
			} while (c.moveToNext());
		}
		return scores;
	}
}
