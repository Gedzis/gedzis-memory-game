package net.gedzis.memory.model;

import android.graphics.drawable.Drawable;

public class ImageHolder {
	private Drawable smallImage;
	private Drawable bigImage;

	public ImageHolder(Drawable smallImage, Drawable bigImage) {
		super();
		this.smallImage = smallImage;
		this.bigImage = bigImage;
	}

	public Drawable getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(Drawable smallImage) {
		this.smallImage = smallImage;
	}

	public Drawable getBigImage() {
		return bigImage;
	}

	public void setBigImage(Drawable bigImage) {
		this.bigImage = bigImage;
	}

}
