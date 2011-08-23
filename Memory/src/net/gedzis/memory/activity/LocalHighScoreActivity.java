package net.gedzis.memory.activity;

import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.model.PlayerScore;
import android.app.ListActivity;
import android.os.Bundle;

public class LocalHighScoreActivity extends ListActivity {
	/** Called when the activity is first created. */
	private List<PlayerScore> players;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_score_local_layout);
		players = getPlayersDemo();
	}
	
	public List<PlayerScore> getPlayersDemo(){
		return null;
	}
}
