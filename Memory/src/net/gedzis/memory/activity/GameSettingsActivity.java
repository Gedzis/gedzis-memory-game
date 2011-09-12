package net.gedzis.memory.activity;

import net.gedzis.memory.R;
import net.gedzis.memory.common.Constants;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class GameSettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		getWindow().setBackgroundDrawableResource(R.drawable.game_bg);
	}

	public static boolean getVibrate(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(Constants.SETTINGS_VIBRATE,
						Constants.SETTINGS_VIBRATE_DEFAULT);
	}

	/** Get the current value of the music option */
	public static boolean getMusic(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(Constants.SETTINGS_MUSIC,
						Constants.SETTINGS_MUSIC_DEFAULT);
	}

}
