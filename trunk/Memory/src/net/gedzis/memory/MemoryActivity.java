package net.gedzis.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MemoryActivity extends Activity implements OnClickListener {
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
			Intent viewActivityIntent = new Intent(this, CardsActivity.class);
			startActivity(viewActivityIntent);
			break;

		// More buttons go here (if any) ...
		}
	}

	private void openNewGameDialog() {
		// new
		// AlertDialog.Builder(this).setTitle(R.string.select_grid_size_caption)
		// .setItems(R.array.category,
		// new DialogInterface.OnClickListener() {
		// public void onClick(
		// DialogInterface dialoginterface, int i) {
		// startGame(i);
		// }
		// }).show();
	}

}