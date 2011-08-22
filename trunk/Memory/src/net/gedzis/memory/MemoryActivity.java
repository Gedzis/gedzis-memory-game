package net.gedzis.memory;

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
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_game_button:
			openNewGameDialog();
			break;
		}
	}

}