package net.gedzis.memory.dialog;

import net.gedzis.memory.R;
import net.gedzis.memory.activity.GameSettingsActivity;
import net.gedzis.memory.audio.AudioPlayer;
import net.gedzis.memory.common.Common;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.database.Database;
import net.gedzis.memory.model.PlayerScore;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverDialog extends Dialog implements OnClickListener {
	private Database database;
	private Context context;
	public Common common = new Common();

	protected String table;
	protected int turns;
	protected long time;

	public GameOverDialog(Context context, int turns, long time, String table) {
		super(context);
		this.time = time;
		this.turns = turns;
		this.table = table;
		this.context = context;

		setContentView(R.layout.game_over_dialog_layout);
		setTitle(R.string.game_over_dialog_title);

		TextView turnsTextView = (TextView) findViewById(R.id.game_over_turns);
		turnsTextView.setText(Integer.toString(turns));

		TextView elapsedTimeTextView = (TextView) findViewById(R.id.game_over_timmer);
		elapsedTimeTextView.setText(common.timeToString(time));

		EditText userNameInput = (EditText) findViewById(R.id.user_name_input);
		userNameInput.setText(GameSettingsActivity.getUserName(context));
		// set up image view
		ImageView img = (ImageView) findViewById(R.id.game_over_logo);
		img.setImageResource(R.drawable.logo);
		Button closeDialogButton = (Button) findViewById(R.id.game_over_button_close);
		closeDialogButton.setOnClickListener(this);

		Button submitLocalScore = (Button) findViewById(R.id.game_over_button_submit_score_local);
		submitLocalScore.setOnClickListener(this);
		getWindow().setBackgroundDrawableResource(R.drawable.game_bg);

	}

	// TODO Auto-generated constructor stub

	public void onClick(View v) {
		AudioPlayer.play(this.getContext(), R.raw.button);

		switch (v.getId()) {
		case R.id.game_over_button_close:
			this.dismiss();
			break;
		case R.id.game_over_button_submit_score_local:
			saveScore();
			break;
		}

	}

	public void saveScore() {
		EditText userNameField = (EditText) findViewById(R.id.user_name_input);
		String userName = userNameField.getText().toString();
		if (userName.equals("")) {
			userName = GameSettingsActivity.getUserName(context);
		} else {
			if (!userName.toLowerCase().equals(
					GameSettingsActivity.getUserName(context).toLowerCase())) {
				SharedPreferences gameSettings = GameSettingsActivity
						.getSharedPreferences(context);
				SharedPreferences.Editor prefEditor = gameSettings.edit();
				prefEditor.putString(Constants.SETTINGS_USER_NAME, userName);
				prefEditor.commit();

			}
		}
		if (userName.equals("")) {
		} else {
			database = new Database(context);
			database.open();
			database.insertPlayerScore(new PlayerScore(userName, turns, time,
					table));
			database.close();
			this.dismiss();
			Toast.makeText(context, R.string.score_saved, Toast.LENGTH_SHORT)
					.show();

		}

	}

}
