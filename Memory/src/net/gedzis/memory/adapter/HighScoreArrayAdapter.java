package net.gedzis.memory.adapter;

import java.util.List;

import net.gedzis.memory.R;
import net.gedzis.memory.model.PlayerScore;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighScoreArrayAdapter extends ArrayAdapter<PlayerScore> {
	private Context context;
	private List<PlayerScore> highscores;
	private int place = 0;

	public HighScoreArrayAdapter(Context context, int textViewResourceId,
			List<PlayerScore> objects) {
		super(context, textViewResourceId, objects);
		this.highscores = objects;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.high_score_element, null);
		}
		PlayerScore o = highscores.get(position);
		if (o != null) {
			TextView highScorePlace = (TextView) v
					.findViewById(R.id.local_highscore_place);
			TextView highScoreName = (TextView) v
					.findViewById(R.id.local_highscore_name);
			TextView highScoreTurns = (TextView) v
					.findViewById(R.id.local_highscore_turns);
			TextView highScoreTime = (TextView) v
					.findViewById(R.id.local_highscore_time);
			if (highScorePlace != null) {
				highScorePlace.setText(Integer.toString(place));
			}
			if (highScoreName != null) {
				highScoreName.setText(o.getName());
			}
			if (highScoreTurns != null) {
				highScoreTurns.setText(o.getTurns());
			}
			if (highScoreTime != null) {
				highScoreTime.setText(o.getTime());
			}
		}
		return v;
	}
}
