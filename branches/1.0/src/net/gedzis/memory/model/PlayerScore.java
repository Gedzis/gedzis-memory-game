package net.gedzis.memory.model;
//http://www.i-coding.de/www/en/java/list/sorting.html
public class PlayerScore {
	private String name;
	private int turns;
	private long time;
	private String table;

	public PlayerScore() {
		super();
	}

	public PlayerScore(String name, int turns, long time, String table) {
		super();
		this.name = name;
		this.turns = turns;
		this.time = time;
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
