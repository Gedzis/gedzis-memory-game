package net.gedzis.memory.database;

public class DatabaseConstants {
	public static String DATABASE_NAME = "memory-db";
	public static final int DATABASE_VERSION = 3;

	public static final String CARD_GAME_LOCAL_HIGH_SCORE_TABLE_NAME = "localscore";
	public static String CARD_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ CARD_GAME_LOCAL_HIGH_SCORE_TABLE_NAME
			+ "("
			+ "name          TEXT NOT NULL, "
			+ "tableid          TEXT NOT NULL, "
			+ "turns                 INTEGER NOT NULL, "
			+ "time                 INTEGER NOT NULL );";
	public static final String TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_NAME = "timerushlocalscore";
	public static String TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TIME_RUSH_GAME_LOCAL_HIGH_SCORE_TABLE_NAME
			+ "("
			+ "name          TEXT NOT NULL, "
			+ "correct                 INTEGER NOT NULL, "
			+ "time                 INTEGER NOT NULL );";
}
// CREATE TABLE local_high_score(name TEXT NOT NULL, turns INTEGER NOT NULL,time
// INTEGER NOT NULL,table TEXT NOT NULL);