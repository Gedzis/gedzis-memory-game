package net.gedzis.memory;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.common.Common;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.GameTable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MemoryActivity extends Activity implements OnClickListener {
	private Common common = new Common();
	private List<GameTable> gameTables;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gameTables = common.getGameTables();
		View newButton = findViewById(R.id.new_game_button);
		newButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_game_button:
			openNewGameDialog();
			break;

		// More buttons go here (if any) ...
		}
	}

	private void openNewGameDialog() {
		ArrayList<String> captions = new ArrayList<String>();
		for (GameTable gt : gameTables) {
			captions.add(gt.toString());
		}
		new AlertDialog.Builder(this).setTitle(R.string.select_grid_size_title)
				.setItems(captions.toArray(new CharSequence[captions.size()]),
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startGame(i);
							}
						}).show();
	}

	public void startGame(int selected) {
		Intent viewActivityIntent = new Intent(this, CardsGameActivity.class);
		viewActivityIntent.putExtra(Constants.GAME_TABLE_COL, gameTables.get(
				selected).getColumns());
		viewActivityIntent.putExtra(Constants.GAME_TABLE_ROW, gameTables.get(
				selected).getRows());
		startActivity(viewActivityIntent);
	}

}