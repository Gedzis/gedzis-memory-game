package net.gedzis.memory;

import net.gedzis.memory.activity.GameSettingsActivity;
import net.gedzis.memory.activity.LocalHighScoreActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MemoryActivity extends BaseActivity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		View newButton = findViewById(R.id.new_game_button);
		newButton.setOnClickListener(this);
		View settingsButton = findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(this);
		View localHistoryButton = findViewById(R.id.local_highscore_button);
		localHistoryButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_game_button:
			openNewGameDialog();
			break;
		case R.id.settings_button:
			startActivity(new Intent(this, GameSettingsActivity.class));
			break;
		case R.id.local_highscore_button:
			startActivity(new Intent(this, LocalHighScoreActivity.class));
			break;
		}
	}

}