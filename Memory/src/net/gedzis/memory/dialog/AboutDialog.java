package net.gedzis.memory.dialog;

import net.gedzis.memory.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutDialog extends Dialog implements OnClickListener {

	public AboutDialog(Context context) {
		super(context, R.style.NoTitleDialog);
		setContentView(R.layout.about_dialog_layout);

		Button closeButton = (Button) findViewById(R.id.about_close_button);
		closeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_close_button:
			this.dismiss();
			break;
		}
	}

}
