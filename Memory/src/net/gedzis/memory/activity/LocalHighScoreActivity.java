package net.gedzis.memory.activity;

import java.util.List;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.audio.AudioPlayer;
import net.gedzis.memory.model.GameTable;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LocalHighScoreActivity extends BaseActivity implements
		OnClickListener {
	public List<GameTable> gameTables;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_high_score_layout);
		gameTables = common.getCardGameTables();

		View cardGameButton = findViewById(R.id.card_game_score);
		cardGameButton.setOnClickListener(this);
		View timeRushGameButton = findViewById(R.id.time_rush_score);
		timeRushGameButton.setOnClickListener(this);

	}

	public void onClick(View v) {
		AudioPlayer.play(this, R.raw.button);
		switch (v.getId()) {
		case R.id.card_game_score:
			vibrator.vibrate(getVibrationIntensity());
			startActivity(new Intent(this, CardGameLocalHighScoreActivity.class));
			break;
		case R.id.time_rush_score:
			vibrator.vibrate(getVibrationIntensity());
			startActivity(new Intent(this,
					TimeRushGameLocalHighScoreActivity.class));
			break;

		}
	}

}
