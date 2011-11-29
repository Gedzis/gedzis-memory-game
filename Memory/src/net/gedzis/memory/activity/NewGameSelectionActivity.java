package net.gedzis.memory.activity;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.audio.AudioPlayer;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.GameTable;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class NewGameSelectionActivity extends BaseActivity implements
		OnClickListener {
	public List<GameTable> gameTables;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game_selection_layout);
		gameTables = common.getCardGameTables();

		View cardGameButton = findViewById(R.id.card_game);
		cardGameButton.setOnClickListener(this);
		View timeRushGameButton = findViewById(R.id.time_rush);
		timeRushGameButton.setOnClickListener(this);

	}

	public void onClick(View v) {
		AudioPlayer.play(this, R.raw.button);
		switch (v.getId()) {
		case R.id.card_game:
			vibrator.vibrate(getVibrationIntensity());
			openNewCardGameDialog();
			break;
		case R.id.time_rush:
			vibrator.vibrate(getVibrationIntensity());
			startActivity(new Intent(this, TimeRushGameActivity.class));
			break;

		}
	}

	public void openNewCardGameDialog() {
		ArrayList<String> captions = new ArrayList<String>();
		for (GameTable gt : gameTables) {
			captions.add(gt.toString());
		}
		new AlertDialog.Builder(this).setTitle(R.string.select_grid_size_title)
				.setItems(captions.toArray(new CharSequence[captions.size()]),
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startCardGame(i);
							}
						}).show();
	}

	public void startCardGame(int selected) {
		Intent viewActivityIntent = new Intent(this, CardsGameActivity.class);
		viewActivityIntent.putExtra(Constants.GAME_TABLE_COL, gameTables.get(
				selected).getColumns());
		viewActivityIntent.putExtra(Constants.GAME_TABLE_ROW, gameTables.get(
				selected).getRows());
		startActivity(viewActivityIntent);
	}
}
