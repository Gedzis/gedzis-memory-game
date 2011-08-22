package net.gedzis.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.Card;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CardsGameActivity extends BaseActivity {
	private static Object lock = new Object();
	private ButtonListener buttonListener;
	private static int TABLE_ROW_COUNT = -1;
	private static int TABLE_COL_COUNT = -1;
	private int[][] gameTable;
	private List<Drawable> images;
	private Drawable backImage;
	private TableLayout mainTable;
	private Card firstCard;
	private Card secondCard;
	int turns;
	int corretGuess;
	private UpdateCardsHandler handler;

	private TextView turnsCaption;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_game_layout);
		turnsCaption = ((TextView) findViewById(R.id.turns_caption));
		images = common.loadImages(this);
		backImage = common.getBackImage(this);
		buttonListener = new ButtonListener();
		handler = new UpdateCardsHandler();
		newGame(getIntent().getIntExtra(Constants.GAME_TABLE_ROW,
				Constants.DEFAULT_GAME_TABLE_SIZE_ROW), getIntent()
				.getIntExtra(Constants.GAME_TABLE_COL,
						Constants.DEFAULT_GAME_TABLE_SIZE_COL));
	}

	public void updateTurnsCaption() {
		turnsCaption
				.setText(getString(R.string.turns_title_text) + " " + turns);
	}

	public void newGame(int row, int col) {
		TABLE_COL_COUNT = col;
		TABLE_ROW_COUNT = row;
		gameTable = new int[TABLE_COL_COUNT][TABLE_ROW_COUNT];
		mainTable = (TableLayout) findViewById(R.id.game_table);
		for (int y = 0; y < TABLE_ROW_COUNT; y++) {
			mainTable.addView(createRow(y));
		}
		corretGuess = 0;
		turns = 0;
		updateTurnsCaption();
		firstCard = null;
		secondCard = null;
		loadCards();
	}

	public void gameOver() {
		Toast.makeText(this, "Žadimas baigtas! ", Toast.LENGTH_SHORT).show();

	}

	private TableRow createRow(int y) {
		TableRow row = new TableRow(mainTable.getContext());
		row.setHorizontalGravity(Gravity.CENTER);

		for (int x = 0; x < TABLE_COL_COUNT; x++) {
			row.addView(createImageButton(x, y));
		}
		return row;
	}

	private View createImageButton(int x, int y) {
		Button button = new Button(mainTable.getContext());
		button.setBackgroundDrawable(backImage);
		button.setId(100 * x + y);
		button.setOnClickListener(buttonListener);
		return button;
	}

	private void loadCards() {
		try {
			int size = TABLE_ROW_COUNT * TABLE_COL_COUNT;
			Random r = new Random();

			Log.i("loadCards()", "size=" + size);

			ArrayList<Integer> list = new ArrayList<Integer>();

			for (int i = 0; i < size; i++) {
				list.add(new Integer(i));
			}
			for (int i = size - 1; i >= 0; i--) {
				int t = 0;
				if (i > 0) {
					t = r.nextInt(i);
				}
				t = list.remove(t).intValue();
				gameTable[i % TABLE_COL_COUNT][i / TABLE_COL_COUNT] = t
						% (size / 2);

				Log.i("loadCards()", "card[" + (i % TABLE_COL_COUNT) + "]["
						+ (i / TABLE_COL_COUNT) + "]="
						+ gameTable[i % TABLE_COL_COUNT][i / TABLE_COL_COUNT]);
			}
		} catch (Exception e) {
			Log.e("loadCards()", e + "");
		}

	}

	class ButtonListener implements OnClickListener {

		public void onClick(View v) {

			synchronized (lock) {
				if (firstCard != null && secondCard != null) {
					return;
				}
				int id = v.getId();
				int x = id / 100;
				int y = id % 100;
				turnCard((Button) v, x, y);
			}

		}

		private void turnCard(Button button, int x, int y) {
			button.setBackgroundDrawable(images.get(gameTable[x][y]));
			if (firstCard == null) {
				firstCard = new Card(button, x, y);
			} else {
				if (firstCard.x == x && firstCard.y == y) {
					return; // the user pressed the same card
				}
				secondCard = new Card(button, x, y);
				turns++;
				updateTurnsCaption();
				TimerTask tt = new TimerTask() {

					@Override
					public void run() {
						try {
							synchronized (lock) {
								handler.sendEmptyMessage(0);
							}
						} catch (Exception e) {
							Log.e("E1", e.getMessage());
						}
					}
				};

				Timer t = new Timer(false);
				t.schedule(tt, 1300);
			}
		}
	}

	class UpdateCardsHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			synchronized (lock) {
				checkCards();
			}
		}

		public void checkCards() {
			if (gameTable[secondCard.x][secondCard.y] == gameTable[firstCard.x][firstCard.y]) {
				firstCard.button.setVisibility(View.INVISIBLE);
				secondCard.button.setVisibility(View.INVISIBLE);
				corretGuess++;
			} else {
				secondCard.button.setBackgroundDrawable(backImage);
				firstCard.button.setBackgroundDrawable(backImage);
			}
			firstCard = null;
			secondCard = null;
			if (corretGuess == TABLE_ROW_COUNT * TABLE_COL_COUNT / 2) {
				gameOver();
			}
		}
	}
}
