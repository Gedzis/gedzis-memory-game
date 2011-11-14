package net.gedzis.memory;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.activity.CardsGameActivity;
import net.gedzis.memory.activity.GameSettingsActivity;
import net.gedzis.memory.activity.LocalHighScoreActivity;
import net.gedzis.memory.common.Common;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.GameTable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	public Common common = new Common();
	public List<GameTable> gameTables;
	public Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameTables = common.getGameTables();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_new_game:
			openNewGameDialog();
			return true;
		case R.id.menu_settings:
			Intent settingsActivityIntent = new Intent(this,
					GameSettingsActivity.class);
			startActivity(settingsActivityIntent);
			return true;
		case R.id.menu_high_score:
			startActivity(new Intent(this, LocalHighScoreActivity.class));
		}

		return false;
	}

	public void openNewGameDialog() {
		ArrayList<String> captions = new ArrayList<String>();
		for (GameTable gt : gameTables) {
			captions.add(gt.toString());
		}
		new AlertDialog.Builder(this).setTitle(R.string.select_grid_size_title)
				.setItems(captions.toArray(new CharSequence[captions.size()]),
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startGame(i);
							}
						}).show();
	}

	public long getVibrationIntensity() {
		if (!GameSettingsActivity.getVibrate(this.getApplicationContext())) {
			return new Long(0);
		} else
			return Constants.VIBRATE_ITENSITY;
	}

	public void startGame(int selected) {
		Intent viewActivityIntent = new Intent(this, CardsGameActivity.class);
		viewActivityIntent.putExtra(Constants.GAME_TABLE_COL, gameTables.get(
				selected).getColumns());
		viewActivityIntent.putExtra(Constants.GAME_TABLE_ROW, gameTables.get(
				selected).getRows());
		startActivity(viewActivityIntent);
	}

}
