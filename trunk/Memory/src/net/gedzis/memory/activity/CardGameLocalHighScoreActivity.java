package net.gedzis.memory.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.adapter.CardGameScoreArrayAdapter;
import net.gedzis.memory.comparator.CardGamePlayerScoreComparator;
import net.gedzis.memory.database.Database;
import net.gedzis.memory.database.DatabaseCommon;
import net.gedzis.memory.model.PlayerScore;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CardGameLocalHighScoreActivity extends BaseActivity {

	/** Called when the activity is first created. */
	private List<PlayerScore> players;
	private Database database;
	private LinearLayout layout;
	private Comparator<PlayerScore> scoreComparator = new CardGamePlayerScoreComparator();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new Database(this);
		database.open();
		setContentView(R.layout.card_game_score_layout);
		players = getDBRecords();

		// LocalMemoryXMLParser localMemoryXMLParser = new
		// LocalMemoryXMLParser();
		// players = localMemoryXMLParser.parseFile(this);
		// http://groups.google.com/group/android-beginners/browse_thread/thread/55ee7bee074d3efc/c2407a62aaada5d0?pli=1
		List<String> tableIds = common.getCardGameTableIDs();
		layout = (LinearLayout) findViewById(R.id.local_high_score_base);

		LinearLayout idsList = (LinearLayout) findViewById(R.id.table_id_list);
		TableIDClickListener idClickListener = new TableIDClickListener();
		for (String id : tableIds) {
			Button tableIdButton = new Button(layout.getContext());
			tableIdButton.setOnClickListener(idClickListener);
			tableIdButton.setText(id);
			idsList.addView(tableIdButton);
		}
		displayTableIdScores(tableIds.get(0));
		database.close();

	}

	public List<PlayerScore> getDBRecords() {
		Cursor c = database.getCardGameScores();
		return DatabaseCommon.getCardGamePlayerScoreList(c);
	}

	public void displayTableIdScores(String tableId) {
		TextView tableName = (TextView) findViewById(R.id.local_highscore_table_name);
		TextView noScore = (TextView) findViewById(R.id.no_score_text_view);
		tableName.setText(getText(R.string.local_highscore_list_caption) + " "
				+ tableId);
		ListView list = (ListView) findViewById(R.id.local_high_score_list);
		List<PlayerScore> scores = common.getCurrentTableHighScore(players,
				tableId);
		if (scores.size() == 0) {
			noScore.setVisibility(View.VISIBLE);
		} else {
			noScore.setVisibility(View.INVISIBLE);
		}
		Collections.sort(scores, scoreComparator);

		CardGameScoreArrayAdapter highScoreArrayAdapter = new CardGameScoreArrayAdapter(
				this, R.layout.card_game_score_element, scores);
		list.setAdapter(highScoreArrayAdapter);
	}

	class TableIDClickListener implements OnClickListener {

		public void onClick(View v) {
			Button tableIdButton = (Button) v;
			displayTableIdScores(tableIdButton.getText().toString());
		}

	}
}
