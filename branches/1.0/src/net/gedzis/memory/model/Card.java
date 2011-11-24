package net.gedzis.memory.model;

import android.widget.ImageView;

public class Card {
	private int x;
	private int y;
	private ImageView button;

	public Card(ImageView button, int x, int y) {
		this.x = x;
		this.y = y;
		this.button = button;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageView getButton() {
		return button;
	}

	public void setButton(ImageView button) {
		this.button = button;
	}

}
