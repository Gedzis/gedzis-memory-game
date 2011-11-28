package net.gedzis.memory.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.gedzis.memory.BaseActivity;
import net.gedzis.memory.R;
import net.gedzis.memory.animation.ChangeViewBackground;
import net.gedzis.memory.animation.Flip3dAnimation;
import net.gedzis.memory.common.Constants;
import net.gedzis.memory.dialog.TimeRushGameGameOverDialog;
import net.gedzis.memory.model.Card;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class TimeRushGameActivity extends BaseActivity {
	private static Object lock = new Object();
	private CardClickListener cardClickListener;
	private CheckCardsHandler checkCardsHandler;
	/** Game images */
	public List<Drawable> images;
	private Drawable backImage;
	private TextView correctGuessesCaption;
	private ImageView newGameButton;
	private ImageView localHighScoreButton;

	/** Game variables */
	private int correct;
	private boolean gameStarted = false;

	/** Timmer */
	private Chronometer chrono;
	long elapsedTime;
	String currentTime = "";
	long startTime = SystemClock.elapsedRealtime();

	/** Images */
	private ImageView mainImage;
	private LinearLayout lineOne;
	private LinearLayout lineTwo;

	/** Main cards */
	private int mainCard;
	private Card selectedCard;
	private int[][] gameTable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backImage = common.getBackImage(this);
		images = common.loadImages(this);
		setContentView(R.layout.time_game_layout);
		cardClickListener = new CardClickListener();
		checkCardsHandler = new CheckCardsHandler();

		// Find all cards
		mainImage = (ImageView) findViewById(R.id.main_image);
		lineOne = (LinearLayout) findViewById(R.id.line_one);
		lineTwo = (LinearLayout) findViewById(R.id.line_two);
		correctGuessesCaption = (TextView) findViewById(R.id.correct_guesses_caption);
		newGameButton = (ImageView) findViewById(R.id.game_new_game_button);
		newGameButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),
						TimeRushGameActivity.class));
			}
		});
		localHighScoreButton = (ImageView) findViewById(R.id.game_local_highscore_button);
		localHighScoreButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

			}
		});
		chrono = (Chronometer) findViewById(R.id.timmer);
		chrono.setOnChronometerTickListener(new OnChronometerTickListener() {

			public void onChronometerTick(Chronometer arg0) {
				elapsedTime = elapsedTime - 1000;
				long minutes = common.getMinutesValue(elapsedTime);
				long seconds = common.getSecondsValue(elapsedTime);
				currentTime = common.timeToString(minutes, seconds);
				arg0.setText(currentTime);
				if (elapsedTime == 0) {
					if (!gameStarted) {
						startGame();
					} else {
						gameOver();
					}
				}
			}
		});
		prepareGame();

	}

	public void resetChrono() {
		chrono.stop();
		chrono.setBase(SystemClock.elapsedRealtime());
	}

	public void startChrono(long time) {
		resetChrono();
		elapsedTime = time;
		chrono.setText(common.timeToString(time));
		chrono.start();
	}

	public void stopChrono() {
		chrono.stop();
		resetChrono();

	}

	public void prepareGame() {
		newGameButton.setVisibility(View.GONE);
		localHighScoreButton.setVisibility(View.GONE);
		lineOne.setVisibility(View.VISIBLE);
		lineTwo.setVisibility(View.VISIBLE);
		mainImage.setVisibility(View.VISIBLE);

		gameTable = loadCards();
		drawImages();
		// mainImage.setBackgroundDrawable(backImage);
		// resetChronoOneMin();
		startChrono(10000);
		correctGuessesCaption.setVisibility(View.INVISIBLE);
	}

	public void startGame() {
		gameStarted = true;
		correct = 0;
		setMainGameCard();
		closeGameTableImages();
		correctGuessesCaption.setVisibility(View.VISIBLE);
		updateCorrectGuessesCaption();
		startChrono(Constants.CHRONO_ONE_MIN);
	}

	public void gameOver() {
		chrono.stop();

		mainImage.setVisibility(View.GONE);
		lineOne.setVisibility(View.GONE);
		lineTwo.setVisibility(View.GONE);

		newGameButton.setVisibility(View.VISIBLE);
		localHighScoreButton.setVisibility(View.VISIBLE);

		gameStarted = false;
		Dialog gameOverDialog = new TimeRushGameGameOverDialog(this, correct);
		gameOverDialog.show();

	}

	public void setMainGameCard() {
		Random r = new Random();
		mainCard = r.nextInt(Constants.TIME_GAME_ROWS
				* Constants.TIME_GAME_COLUMNS);
		if (mainCard == 0) {
			mainCard++;
		}
		applyRotation(mainImage, images.get(mainCard), 0, 90);

	}

	public void closeGameTableImages() {
		changeLayoutChildrenBackground(lineOne);
		changeLayoutChildrenBackground(lineTwo);
	}

	private void changeLayoutChildrenBackground(LinearLayout layout) {
		int count = layout.getChildCount();
		View v = null;
		for (int i = 0; i < count; i++) {
			v = layout.getChildAt(i);
			applyRotation((ImageView) v, backImage, 0, 90);
		}
	}

	public void drawImages() {
		for (int y = 0; y < Constants.TIME_GAME_COLUMNS; y++) {
			lineOne.addView(createImageButton(0, y, lineOne));
			lineTwo.addView(createImageButton(1, y, lineTwo));
		}
	}

	private View createImageButton(int x, int y, LinearLayout layout) {
		ImageView button = new ImageView(layout.getContext());
		button.setBackgroundDrawable(images.get(gameTable[x][y]));
		button.setId(100 * x + y);
		button.setOnClickListener(cardClickListener);
		button.setPadding(5, 0, 5, 0);
		return button;
	}

	public int[][] loadCards() {
		int[][] generatedGameTable = new int[Constants.TIME_GAME_ROWS][Constants.TIME_GAME_COLUMNS];
		;
		int size = Constants.TIME_GAME_ROWS * Constants.TIME_GAME_COLUMNS;
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++)
			numbers.add(i);
		Collections.shuffle(numbers);

		int index = 0;
		for (int row = 0; row < generatedGameTable.length; row++) {
			for (int col = 0; col < generatedGameTable[row].length; col++)
				generatedGameTable[row][col] = numbers.get(index++);
		}
		return generatedGameTable;
	}

	private void applyRotation(ImageView currentView, Drawable imageToReplace,
			float start, float end) {
		// Find the center of image
		final float centerX = currentView.getWidth() / 2.0f;
		final float centerY = currentView.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(Constants.FLIP_DURATION_TIME);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new ChangeViewBackground(currentView,
				imageToReplace));
		currentView.startAnimation(rotation);

	}

	public void updateCorrectGuessesCaption() {
		correctGuessesCaption
				.setText(getString(R.string.time_game_correct_guesses) + " "
						+ correct);
	}

	class CardClickListener implements OnClickListener {

		public void onClick(View v) {

			synchronized (lock) {
				if (selectedCard != null) {
					return;
				}
				int id = v.getId();
				int x = id / 100;
				int y = id % 100;
				if (gameStarted) {
					turnCard((ImageView) v, x, y);
				}
			}

		}

		private void turnCard(ImageView button, int x, int y) {
			applyRotation(button, images.get(gameTable[x][y]), 0, 90);
			if (selectedCard == null) {
				selectedCard = new Card(button, x, y);
				TimerTask tt = new TimerTask() {

					@Override
					public void run() {
						try {
							synchronized (lock) {
								checkCardsHandler.sendEmptyMessage(0);
							}
						} catch (Exception e) {
							Log.e("E1", e.getMessage());
						}
					}
				};

				Timer t = new Timer(false);
				t.schedule(tt, Constants.TIMMER_TASK_DELAY);

			}
		}
	}

	class CheckCardsHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			synchronized (lock) {
				checkCards();
			}
		}

		public void checkCards() {
			if (gameTable[selectedCard.getX()][selectedCard.getY()] == mainCard) {
				correct++;

			} else {
			}
			setMainGameCard();
			applyRotation(selectedCard.getButton(), backImage, 0, 90);
			selectedCard = null;
			updateCorrectGuessesCaption();
		}
	}
}