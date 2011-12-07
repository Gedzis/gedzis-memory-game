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
import android.widget.TextView;
import android.widget.Toast;

public class TimeRushGameGameOverDialog extends Dialog implements
		OnClickListener {

	private Database database;
	public Common common = new Common();

	protected int correct;
	private Context context;

	public TimeRushGameGameOverDialog(Context context, int correct) {
		super(context);
		this.correct = correct;
		this.context = context;

		setContentView(R.layout.game_over_dialog_layout);
		setTitle(R.string.game_over_dialog_title);

		TextView turnsTextView = (TextView) findViewById(R.id.turns_text);
		turnsTextView.setText(R.string.time_game_correct_guesses);

		TextView turnsTextAmountView = (TextView) findViewById(R.id.game_over_turns);
		turnsTextAmountView.setText(Integer.toString(correct));

		EditText userNameInput = (EditText) findViewById(R.id.user_name_input);
		userNameInput.setText(GameSettingsActivity.getUserName(context));

		// Hide fields
		TextView elapsedTimeTextAmountView = (TextView) findViewById(R.id.game_over_timmer);
		elapsedTimeTextAmountView.setVisibility(View.INVISIBLE);

		TextView elapsedTimeTextView = (TextView) findViewById(R.id.time_elapsed_caption);
		elapsedTimeTextView.setVisibility(View.INVISIBLE);

		Button closeDialogButton = (Button) findViewById(R.id.game_over_button_close);
		closeDialogButton.setOnClickListener(this);

		Button submitLocalScore = (Button) findViewById(R.id.game_over_button_submit_score_local);
		submitLocalScore.setOnClickListener(this);
		getWindow().setBackgroundDrawableResource(R.drawable.game_bg);
	}

	@Override
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
			long saveSuccesful = database
					.insertTimeRushGamePlayerScore(new PlayerScore(userName,
							correct, 0, null));
			database.close();
			this.dismiss();
			if (saveSuccesful >= 0) {
				Toast.makeText(context, R.string.score_saved,
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, R.string.score_save_error,
						Toast.LENGTH_SHORT).show();
			}

		}

	}
}
