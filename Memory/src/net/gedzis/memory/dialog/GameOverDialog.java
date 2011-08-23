package net.gedzis.memory.dialog;

import net.gedzis.memory.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverDialog extends Dialog implements OnClickListener {

	public GameOverDialog(Context context, int turns, String time) {
		super(context);
		setContentView(R.layout.game_over_dialog_layout);
		setTitle(R.string.game_over_dialog_title);

		TextView turnsTextView = (TextView) findViewById(R.id.game_over_turns);
		turnsTextView.setText(Integer.toString(turns));

		TextView elapsedTimeTextView = (TextView) findViewById(R.id.game_over_timmer);
		elapsedTimeTextView.setText(time);

		// set up image view
		ImageView img = (ImageView) findViewById(R.id.game_over_logo);
		img.setImageResource(R.drawable.card1);
		Button closeDialogButton = (Button) findViewById(R.id.game_over_button_close);
		closeDialogButton.setOnClickListener(this);
	}

	// TODO Auto-generated constructor stub

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.game_over_button_close:
			this.dismiss();
			break;
		}

	}

}
