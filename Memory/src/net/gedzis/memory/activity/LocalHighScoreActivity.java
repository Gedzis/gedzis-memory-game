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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class LocalHighScoreActivity extends BaseActivity {

	// <TextView android:id="@+id/local_highscore_2x2_caption"
	// android:layout_width="fill_parent" android:layout_height="wrap_content"
	// android:text="2x2" android:layout_below="@+id/listTitles" />
	// <ListView android:id="@+id/high_score_2x2_list"
	// android:layout_width="fill_parent" android:layout_height="wrap_content"
	// android:layout_below="@+id/local_highscore_2x2_caption"
	// android:clickable="false"
	// android:listSelector="@android:color/transparent" />
	//
	// <TextView android:id="@+id/local_highscore_3x3_caption"
	// android:layout_width="fill_parent" android:layout_height="wrap_content"
	// android:text="3x3" android:layout_below="@+id/high_score_2x2_list" />
	// <ListView android:id="@+id/high_score_3x3_list"
	// android:layout_width="fill_parent" android:layout_height="wrap_content"
	// android:layout_below="@+id/local_highscore_3x3_caption"
	// android:clickable="false"
	// android:listSelector="@android:color/transparent" />

	/** Called when the activity is first created. */
	private List<PlayerScore> players;
	private HighScoreArrayAdapter highScoreArrayAdapter;
	private Database database;

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

		List<String> tableIds = common.getGameTableIDs();
		LinearLayout layout = (LinearLayout) findViewById(R.id.high_score_layout);
		// RelativeLayout header = (RelativeLayout)
		// findViewById(R.id.list_header2);
		for (String id : tableIds) {
			TextView tableCaption = new TextView(layout.getContext());
			tableCaption.setText(getText(R.string.local_highscore_list_caption)
					+ id);
			ListView list = new ListView(layout.getContext());
			highScoreArrayAdapter = new HighScoreArrayAdapter(this,
					R.layout.high_score_element,
					common.getCurrentTableHighScore(players, id));
			list.setAdapter(highScoreArrayAdapter);
			layout.addView(tableCaption);
			layout.addView(list);
		}

	}

	public List<PlayerScore> getDBRecords() {
		Cursor c = database.getScores();
		return DatabaseCommon.getPlayerScoreList(c);
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
		players.add(new PlayerScore("Jonas2", 5, 34, "2x3"));
		players.add(new PlayerScore("Saulius2", 5, 6666, "3x4"));
		players.add(new PlayerScore("Mindaugas2", 5, 34, "4x4"));
		players.add(new PlayerScore("Tomas2", 5, 34, "5x4"));
		players.add(new PlayerScore("Tomas3", 5, 34, "1x2"));
		players.add(new PlayerScore("Marius3", 5, 34, "2x2"));
		players.add(new PlayerScore("Jonas3", 5, 34, "2x3"));
		players.add(new PlayerScore("Saulius3", 5, 6666, "3x4"));
		players.add(new PlayerScore("Mindaugas3", 5, 34, "4x4"));
		players.add(new PlayerScore("Tomas3", 5, 34, "5x4"));
		players.add(new PlayerScore("Tomas4", 5, 34, "1x2"));
		players.add(new PlayerScore("Marius4", 5, 34, "2x2"));
		players.add(new PlayerScore("Jonas4", 5, 34, "2x3"));
		players.add(new PlayerScore("Saulius4", 5, 6666, "3x4"));
		players.add(new PlayerScore("Mindaugas4", 5, 34, "4x4"));
		players.add(new PlayerScore("Tomas4", 5, 34, "5x4"));

		return players;
	}
}
