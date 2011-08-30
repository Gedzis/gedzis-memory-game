package net.gedzis.memory.activity;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.adapter.HighScoreArrayAdapter;
import net.gedzis.memory.handler.LocalHighScoreOutputHandler;
import net.gedzis.memory.model.PlayerScore;
import net.gedzis.memory.parser.LocalMemoryXMLParser;
import android.app.ListActivity;
import android.os.Bundle;

public class LocalHighScoreActivity extends ListActivity {
	/** Called when the activity is first created. */
	private List<PlayerScore> players;
	private HighScoreArrayAdapter highScoreArrayAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_score_local_layout);
		players = getPlayersDemo();
		highScoreArrayAdapter = new HighScoreArrayAdapter(this,
				R.layout.high_score_element, players);
		// ArrayAdapter<RecordsJsonObject> adapter = new
		// ArrayAdapter<RecordsJsonObject>(
		// this, R.layout.record_row, R.id.record_name, records);
		// adapter = new ArrayAdapter<String>(this, R.layout.record_row,
		// R.id.record_score, score);
		LocalHighScoreOutputHandler handler = new LocalHighScoreOutputHandler();
		handler.saveScore(players, this);
		LocalMemoryXMLParser localMemoryXMLParser = new LocalMemoryXMLParser();
		players = localMemoryXMLParser.parseFile(this);
		this.setListAdapter(highScoreArrayAdapter);
	}

	public List<PlayerScore> getPlayersDemo() {
		List<PlayerScore> players = new ArrayList<PlayerScore>();
		players.add(new PlayerScore("Tomas", 5, 34, "table"));
		players.add(new PlayerScore("Marius", 5, 34, "table"));
		players.add(new PlayerScore("Jonas", 5, 34, "table"));
		players.add(new PlayerScore("Saulius", 5, 6666, "table"));
		players.add(new PlayerScore("Mindaugas", 5, 34, "table"));

		return players;
	}
}
