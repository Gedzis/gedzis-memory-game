package net.gedzis.memory;

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
	}

	public static boolean getVibrate(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(Constants.SETTINGS_VIBRATE,
						Constants.SETTINGS_VIBRATE_DEFAULT);
	}
}
