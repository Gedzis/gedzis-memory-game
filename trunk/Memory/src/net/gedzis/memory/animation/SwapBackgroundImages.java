package net.gedzis.memory.animation;

import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SwapBackgroundImages implements Runnable{
	private ImageView currentView;
	private Drawable imageToReplace;

	public SwapBackgroundImages(ImageView currentView, Drawable imageToReplace) {
		this.currentView = currentView;
		this.imageToReplace = imageToReplace;
	}

	public void run() {
		final float centerX = currentView.getWidth() / 2.0f;
		final float centerY = currentView.getHeight() / 2.0f;
		Flip3dAnimation rotation;

		currentView.setBackgroundDrawable(imageToReplace);
		currentView.requestFocus();
		rotation = new Flip3dAnimation(-90, 0, centerX, centerY);

		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());

		currentView.startAnimation(rotation);

	}
}
