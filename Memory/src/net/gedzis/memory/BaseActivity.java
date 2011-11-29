package net.gedzis.memory;

import net.gedzis.memory.activity.GameSettingsActivity;
import net.gedzis.memory.activity.LocalHighScoreActivity;
import net.gedzis.memory.activity.NewGameSelectionActivity;
import net.gedzis.memory.audio.AudioPlayer;
import net.gedzis.memory.common.Common;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.dialog.AboutDialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	public Common common = new Common();
	public Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		AudioPlayer.play(this, R.raw.button);

		switch (item.getItemId()) {
		case R.id.menu_new_game:
			startActivity(new Intent(this, NewGameSelectionActivity.class));
			return true;
		case R.id.menu_settings:
			Intent settingsActivityIntent = new Intent(this,
					GameSettingsActivity.class);
			startActivity(settingsActivityIntent);
			return true;
		case R.id.menu_high_score:
			startActivity(new Intent(this, LocalHighScoreActivity.class));
		case R.id.menu_about:
			Dialog aboutDialog = new AboutDialog(this);
			aboutDialog.show();

		}

		return false;
	}

	public long getVibrationIntensity() {
		if (!GameSettingsActivity.getVibrate(this.getApplicationContext())) {
			return new Long(0);
		} else
			return Constants.VIBRATE_ITENSITY;
	}

}
