package net.gedzis.memory.activity;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.adapter.HighScoreArrayAdapter;
import net.gedzis.memory.database.Database;
import net.gedzis.memory.database.DatabaseCommon;
import net.gedzis.memory.model.PlayerScore;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class LocalHighScoreActivity extends BaseActivity {

	/** Called when the activity is first created. */
	private List<PlayerScore> players;
	private HighScoreArrayAdapter highScoreArrayAdapter;
	private Database database;
	private LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new Database(this);
		database.open();
		setContentView(R.layout.high_score_local_layout);
		players = getDBRecords();

		// LocalMemoryXMLParser localMemoryXMLParser = new
		// LocalMemoryXMLParser();
		// players = localMemoryXMLParser.parseFile(this);
		// http://groups.google.com/group/android-beginners/browse_thread/thread/55ee7bee074d3efc/c2407a62aaada5d0?pli=1
		List<String> tableIds = common.getGameTableIDs();
		layout = (LinearLayout) findViewById(R.id.local_high_score_base);

		TableRow tableRow = (TableRow) findViewById(R.id.table_id_list);
		TableIDClickListener idClickListener = new TableIDClickListener();
		for (String id : tableIds) {
			Button tableIdButton = new Button(layout.getContext());
			tableIdButton.setOnClickListener(idClickListener);
			tableIdButton.setText(id);
			tableRow.addView(tableIdButton);
		}
		displayTableIdScores(tableIds.get(0));
		database.close();

	}

	public List<PlayerScore> getDBRecords() {
		Cursor c = database.getScores();
		return DatabaseCommon.getPlayerScoreList(c);
	}

	public void displayTableIdScores(String tableId) {
		TextView tableName = (TextView) findViewById(R.id.local_highscore_table_name);
		tableName.setText(getText(R.string.local_highscore_list_caption) + " "
				+ tableId);
		ListView list = (ListView) findViewById(R.id.local_high_score_list);
		list.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		highScoreArrayAdapter = new HighScoreArrayAdapter(this,
				R.layout.high_score_element, common.getCurrentTableHighScore(
						players, tableId));
		list.setAdapter(highScoreArrayAdapter);
	}

	public List<PlayerScore> getPlayersDemo() {
		List<PlayerScore> players = new ArrayList<PlayerScore>();
		players.add(new PlayerScore("Tomas1", 5, 34, "1x2"));
		players.add(new PlayerScore("Marius1", 5, 34, "2x2"));
		players.add(new PlayerScore("Jonas1", 5, 34, "2x3"));
		players.add(new PlayerScore("Saulius1", 5, 6666, "3x4"));
		players.add(new PlayerScore("Mindaugas1", 5, 34, "4x4"));
		players.add(new PlayerScore("Tomas1", 5, 34, "5x4"));
		players.add(new PlayerScore("Tomas2", 5, 34, "1x2"));
		players.add(new PlayerScore("Marius2", 5, 34, "2x2"));
		return players;
	}

	class TableIDClickListener implements OnClickListener {

		public void onClick(View v) {
			Button tableIdButton = (Button) v;
			displayTableIdScores(tableIdButton.getText().toString());
		}

	}
}
