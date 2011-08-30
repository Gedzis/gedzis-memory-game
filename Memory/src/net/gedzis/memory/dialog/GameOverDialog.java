package net.gedzis.memory.dialog;

import net.gedzis.memory.R;
import net.gedzis.memory.database.Database;
import net.gedzis.memory.model.PlayerScore;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverDialog extends Dialog implements OnClickListener {
	private Database database;
	private Context context;

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
		elapsedTimeTextView.setText(String.valueOf(time));

		// set up image view
		ImageView img = (ImageView) findViewById(R.id.game_over_logo);
		img.setImageResource(R.drawable.card1);
		Button closeDialogButton = (Button) findViewById(R.id.game_over_button_close);
		closeDialogButton.setOnClickListener(this);

		Button submitLocalScore = (Button) findViewById(R.id.game_over_button_submit_score_local);
		submitLocalScore.setOnClickListener(this);
	}

	// TODO Auto-generated constructor stub

	public void onClick(View v) {
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
		} else {
			database = new Database(context);
			database.open();
			database.insertPlayerScore(new PlayerScore(userName, turns, time,
					table));
			database.close();
			this.dismiss();

		}

	}

}
