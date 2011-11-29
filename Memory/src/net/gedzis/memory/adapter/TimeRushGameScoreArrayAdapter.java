package net.gedzis.memory.adapter;

import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.common.Common;
import net.gedzis.memory.model.PlayerScore;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TimeRushGameScoreArrayAdapter extends ArrayAdapter<PlayerScore> {
	public Common common = new Common();
	private Context context;
	private List<PlayerScore> highscores;
	private int place = 0;

	public TimeRushGameScoreArrayAdapter(Context context,
			int textViewResourceId, List<PlayerScore> objects) {
		super(context, textViewResourceId, objects);
		this.highscores = objects;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.time_rush_game_score_element, null);
		}
		PlayerScore o = highscores.get(position);
		if (o != null) {
			place = position + 1;
			TextView highScorePlace = (TextView) v
					.findViewById(R.id.local_highscore_place);
			TextView highScoreName = (TextView) v
					.findViewById(R.id.local_highscore_name);
			TextView highScoreTurns = (TextView) v
					.findViewById(R.id.local_highscore_turns);
			if (highScorePlace != null) {
				highScorePlace.setText(Integer.toString(place));
			}
			if (highScoreName != null) {
				highScoreName.setText(o.getName());
			}
			if (highScoreTurns != null) {
				highScoreTurns.setText(Integer.toString(o.getTurns()));
			}
		}
		return v;
	}
}
