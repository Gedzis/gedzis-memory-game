package net.gedzis.memory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MemorySQLiteOpenHelper extends SQLiteOpenHelper {
	public MemorySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	private static final String LOG_TAG = "memory-db";

	// public MemorySQLiteOpenHelper(Context context) {
	// super(context, DatabaseConstants.DATABASE_NAME, null,
	// DatabaseConstants.DATABASE_VERSION);
	// this.context = context;
	// }

	// public MemorySQLiteOpenHelper(Context context2, String dATABASE_NAME,
	// Object object, int databaseVersion) {
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(LOG_TAG, "Creating initial database");
		try {
			db
					.execSQL(DatabaseConstants.CARD_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE);
			db
					.execSQL(DatabaseConstants.TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE);
		} catch (Exception e) {
			Log.e(LOG_TAG, e.getMessage());
		}
		Log.i(LOG_TAG, "Finished creating database.");
		Log.i(LOG_TAG, "Inserting initial data");
		// InitialData.importInitialData(db, context);
		Log.i(LOG_TAG, "Finished inserting initial data");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL(DatabaseConstants.CARD_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE);
		db
				.execSQL(DatabaseConstants.TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE);
		onCreate(db);
	}

}
