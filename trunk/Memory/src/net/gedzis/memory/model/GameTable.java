package net.gedzis.memory.model;

public class GameTable {
	public GameTable(int columns, int rows) {
		super();
		this.columns = columns;
		this.rows = rows;
	}

	private int columns;
	private int rows;

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "GameTable [columns=" + columns + ", rows=" + rows + "]";
	}

}
