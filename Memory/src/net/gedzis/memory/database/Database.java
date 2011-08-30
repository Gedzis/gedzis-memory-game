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

	public long insertPlayerScore(PlayerScore playerScore) {
		try {
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put("name", playerScore.getName());
			newTaskValue.put("turns", playerScore.getName());
			newTaskValue.put("time", playerScore.getTime());
			newTaskValue.put("table", playerScore.getTable());
			return db.insert(DatabaseConstants.LOCAL_HIGH_SCORE_TABLE_NAME,
					null, newTaskValue);
		} catch (SQLiteException ex) {
			Log.v("Insert into database exception caught", ex.getMessage());
			return -1;
		}
	}

	public Cursor getScores() {
		Cursor c = db.query(DatabaseConstants.LOCAL_HIGH_SCORE_TABLE_NAME,
				null, null, null, null, null, null);
		return c;
	}

	public Cursor getTableScore(String tableId) {
		Cursor c = db.query(DatabaseConstants.LOCAL_HIGH_SCORE_TABLE_NAME,
				null, null, null, null, null, null);
		return c;
	}
}
