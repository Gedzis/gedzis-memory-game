package net.gedzis.memory.dialog;

import net.gedzis.memory.R;
import net.gedzis.memory.audio.AudioPlayer;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimeRushGameGameOverDialog extends Dialog implements
		OnClickListener {

	protected int correct;

	public TimeRushGameGameOverDialog(Context context, int correct) {
		super(context);
		this.correct = correct;
		setContentView(R.layout.game_over_dialog_layout);
		setTitle(R.string.game_over_dialog_title);

		TextView turnsTextView = (TextView) findViewById(R.id.turns_text);
		turnsTextView.setText(R.string.time_game_correct_guesses);

		TextView turnsTextAmountView = (TextView) findViewById(R.id.game_over_turns);
		turnsTextAmountView.setText(Integer.toString(correct));

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
		this.dismiss();

	}
}
