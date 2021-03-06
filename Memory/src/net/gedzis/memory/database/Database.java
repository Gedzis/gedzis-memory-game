package net.gedzis.memory.database;

import net.gedzis.memory.model.PlayerScore;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Database {
	private SQLiteDatabase db;
	private final Context context;
	private final MemorySQLiteOpenHelper dbhelper;

	public Database(Context c) {
		context = c;
		dbhelper = new MemorySQLiteOpenHelper(context,
				DatabaseConstants.DATABASE_NAME, null,
				DatabaseConstants.DATABASE_VERSION);
	}

	public void close() {
		db.close();
	}

	public void open() throws SQLiteException {
		try {
			db = dbhelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			Log.v("Open database exception caught", ex.getMessage());
			db = dbhelper.getReadableDatabase();
		}
	}

	public long insertCardGamePlayerScore(PlayerScore playerScore) {
		try {
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put("name", playerScore.getName());
			newTaskValue.put("turns", playerScore.getTurns());
			newTaskValue.put("time", playerScore.getTime());
			newTaskValue.put("tableid", playerScore.getTable());
			return db.insert(
					DatabaseConstants.CARD_GAME_LOCAL_HIGH_SCORE_TABLE_NAME,
					null, newTaskValue);
		} catch (SQLiteException ex) {
			Log.v("Insert into database exception caught", ex.getMessage());
			return -1;
		}
	}

	public long insertTimeRushGamePlayerScore(PlayerScore playerScore) {
		try {
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put("name", playerScore.getName());
			newTaskValue.put("correct", playerScore.getTurns());
			newTaskValue.put("time", playerScore.getTime());
			return db
					.insert(
							DatabaseConstants.TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_NAME,
							null, newTaskValue);
		} catch (SQLiteException ex) {
			Log.v("Insert into database exception caught", ex.getMessage());
			return -1;
		}
	}

	public Cursor getCardGameScores() {
		Cursor c = db.query(
				DatabaseConstants.CARD_GAME_LOCAL_HIGH_SCORE_TABLE_NAME, null,
				null, null, null, null, null);
		return c;
	}

	public Cursor getTimeRushGameScores() {
		Cursor c = db.query(
				DatabaseConstants.TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_NAME,
				null, null, null, null, null, null);
		return c;
	}

}
