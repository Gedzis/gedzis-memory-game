package net.gedzis.memory.animation;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;

public class ChangeViewBackground implements Animation.AnimationListener {
	private ImageView currentView;
	private Drawable imageToReplace;

	public ChangeViewBackground(ImageView currentView, Drawable imageToReplace) {
		this.currentView = currentView;
		this.imageToReplace = imageToReplace;
	}

	public void onAnimationEnd(Animation animation) {
		currentView.post(new SwapBackgroundImages(currentView, imageToReplace));

	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
