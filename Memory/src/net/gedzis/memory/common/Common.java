package net.gedzis.memory.common;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.model.GameTable;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class Common {

	public List<Drawable> loadImages(Context context) {
		List<Drawable> images = new ArrayList<Drawable>();
		images.add(context.getResources().getDrawable(R.drawable.card1));
		images.add(context.getResources().getDrawable(R.drawable.card2));
		images.add(context.getResources().getDrawable(R.drawable.card3));
		images.add(context.getResources().getDrawable(R.drawable.card4));
		images.add(context.getResources().getDrawable(R.drawable.card5));
		images.add(context.getResources().getDrawable(R.drawable.card6));
		images.add(context.getResources().getDrawable(R.drawable.card7));
		images.add(context.getResources().getDrawable(R.drawable.card8));
		images.add(context.getResources().getDrawable(R.drawable.card9));
		images.add(context.getResources().getDrawable(R.drawable.card10));
		images.add(context.getResources().getDrawable(R.drawable.card11));
		images.add(context.getResources().getDrawable(R.drawable.card12));
		images.add(context.getResources().getDrawable(R.drawable.card13));
		images.add(context.getResources().getDrawable(R.drawable.card14));
		images.add(context.getResources().getDrawable(R.drawable.card15));
		images.add(context.getResources().getDrawable(R.drawable.card16));
		images.add(context.getResources().getDrawable(R.drawable.card17));
		images.add(context.getResources().getDrawable(R.drawable.card18));
		images.add(context.getResources().getDrawable(R.drawable.card19));
		images.add(context.getResources().getDrawable(R.drawable.card20));
		images.add(context.getResources().getDrawable(R.drawable.card21));
		return images;
	}

	public Drawable getBackImage(Context context) {
		return context.getResources().getDrawable(R.drawable.back);
	}

	public List<GameTable> getGameTables() {
		List<GameTable> gameTables = new ArrayList<GameTable>();
		gameTables.add(createNewGameTable(1, 2));
		gameTables.add(createNewGameTable(2, 2));
		gameTables.add(createNewGameTable(2, 3));
		gameTables.add(createNewGameTable(3, 4));
		gameTables.add(createNewGameTable(4, 4));
		gameTables.add(createNewGameTable(6, 6));
		return gameTables;
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

}
