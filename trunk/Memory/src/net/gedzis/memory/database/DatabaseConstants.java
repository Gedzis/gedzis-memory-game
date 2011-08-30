package net.gedzis.memory.database;

public class DatabaseConstants {
	public static String DATABASE_NAME = "memory-db";
	public static final int DATABASE_VERSION = 2;
	public static final String LOCAL_HIGH_SCORE_TABLE_NAME = "local_high_score";
	public static String LOCAL_HIGH_SCORE_TABLE_CREATE = "CREATE TABLE "
			+ LOCAL_HIGH_SCORE_TABLE_NAME + "("
			+ "name          TEXT NOT NULL, "
			+ "turns                 INTEGER NOT NULL,"
			+ "time                 INTEGER NOT NULL,"
			+ "table            TEXT NOT NULL);";
}
