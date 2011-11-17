package net.gedzis.memory.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.model.GameTable;
import net.gedzis.memory.model.PlayerScore;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class Common {

	public List<Drawable> loadImages(Context context) {
		List<Drawable> images = new ArrayList<Drawable>();
		images.add(context.getResources().getDrawable(R.drawable.angry));
		images.add(context.getResources().getDrawable(R.drawable.cheat));
		images.add(context.getResources().getDrawable(R.drawable.confuse));
		images.add(context.getResources().getDrawable(R.drawable.dizzy));
		images.add(context.getResources().getDrawable(R.drawable.errr));
		images.add(context.getResources().getDrawable(R.drawable.happy));
		images.add(context.getResources().getDrawable(R.drawable.hungry));
		images.add(context.getResources().getDrawable(R.drawable.love));
		images.add(context.getResources().getDrawable(R.drawable.sick));
		images.add(context.getResources().getDrawable(R.drawable.smile));
		images.add(context.getResources().getDrawable(R.drawable.touchy));
		images.add(context.getResources().getDrawable(R.drawable.wow));
		Collections.shuffle(images);
		return images;
	}

	public Drawable getBackImage(Context context) {
		return context.getResources().getDrawable(R.drawable.back);
	}

	public List<GameTable> getGameTables() {
		List<GameTable> gameTables = new ArrayList<GameTable>();
		gameTables.add(createNewGameTable(2, 2));
		gameTables.add(createNewGameTable(3, 2));
		gameTables.add(createNewGameTable(4, 3));
		gameTables.add(createNewGameTable(4, 4));
		gameTables.add(createNewGameTable(4, 5));
		gameTables.add(createNewGameTable(4, 6));

		return gameTables;
	}

	public List<String> getGameTableIDs() {
		List<String> ids = new ArrayList<String>();
		for (GameTable gt : getGameTables()) {
			ids.add(generateTableId(gt.getColumns(), gt.getRows()));
		}
		return ids;
	}

	public String generateTableId(int col, int row) {
		return col + "x" + row;
	}

	protected GameTable createNewGameTable(int col, int row) {
		// TODO reikia patikrinti ar tikrai uzteks turimu paveiksleliu
		return new GameTable(col, row);
	}

	public long getMinutesValue(long elapsedTime) {
		return ((elapsedTime) / 1000) / 60;
	}

	public long getSecondsValue(long elapsedTime) {
		return ((elapsedTime) / 1000) % 60;
	}

	public String timeToString(long minutes, long seconds) {
		return (minutes < 10 ? "0" + minutes : minutes) + ":"
				+ (seconds < 10 ? "0" + seconds : seconds);
	}

	public String timeToString(long time) {
		return timeToString(getMinutesValue(time), getSecondsValue(time));
	}

	public List<PlayerScore> getCurrentTableHighScore(
			List<PlayerScore> players, String tableID) {
		List<PlayerScore> tableScores = new ArrayList<PlayerScore>();
		for (PlayerScore score : players) {
			if (score.getTable().equals(tableID)) {
				tableScores.add(score);
			}
		}
		return tableScores;
	}

}
