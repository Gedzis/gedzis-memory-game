package net.gedzis.memory.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.adapter.TimeRushGameScoreArrayAdapter;
import net.gedzis.memory.comparator.TimeRushGamePlayerScoreComparator;
import net.gedzis.memory.database.Database;
import net.gedzis.memory.database.DatabaseCommon;
import net.gedzis.memory.model.PlayerScore;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class TimeRushGameLocalHighScoreActivity extends BaseActivity {

	/** Called when the activity is first created. */
	private List<PlayerScore> players;
	private Database database;
	private Comparator<PlayerScore> scoreComparator = new TimeRushGamePlayerScoreComparator();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new Database(this);
		database.open();
		setContentView(R.layout.time_rush_game_score_layout);
		players = getDBRecords();
		database.close();

		TextView noScore = (TextView) findViewById(R.id.no_score_text_view);
		ListView list = (ListView) findViewById(R.id.local_high_score_list);
		if (players.size() == 0) {
			noScore.setVisibility(View.VISIBLE);
		} else {
			noScore.setVisibility(View.INVISIBLE);
		}
		Collections.sort(players, scoreComparator);

		TimeRushGameScoreArrayAdapter highScoreArrayAdapter = new TimeRushGameScoreArrayAdapter(
				this, R.layout.time_rush_game_score_element, players);
		list.setAdapter(highScoreArrayAdapter);
	}

	public List<PlayerScore> getDBRecords() {
		Cursor c = database.getTimeRushGameScores();
		return DatabaseCommon.getTimeRushGamePlayerScoreList(c);
	}

}
