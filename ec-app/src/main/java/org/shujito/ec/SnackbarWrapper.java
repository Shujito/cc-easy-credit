package org.shujito.ec;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import lombok.NonNull;

/**
 * @author shujito, 8/3/18
 */
public class SnackbarWrapper {
	public static final String TAG = SnackbarWrapper.class.getSimpleName();
	private final View view;
	private final Context context;
	private Snackbar snackbar;

	public SnackbarWrapper(View view) {
		this.view = view;
		this.context = view.getContext();
	}

	public void show(@StringRes int message, int snackDuration) {
		this.show(this.context.getString(message), snackDuration, null, null);
	}

	public void show(@StringRes int message, int snackDuration, @StringRes int actionMessage, View.OnClickListener actionListener) {
		this.show(this.context.getString(message), snackDuration, this.context.getString(actionMessage), actionListener);
	}

	public void show(@StringRes int message, int snackDuration, String actionMessage, View.OnClickListener actionListener) {
		this.show(this.context.getString(message), snackDuration, actionMessage, actionListener);
	}

	public void show(@NonNull String message, int snackDuration) {
		this.show(message, snackDuration, null, null);
	}

	public void show(String message, int snackDuration, int actionMessage, View.OnClickListener onClickListener) {
		this.show(message, snackDuration, this.context.getString(actionMessage), onClickListener);
	}

	public void show(String message, int snackDuration, String actionMessage, View.OnClickListener actionListener) {
		if (this.snackbar != null && this.snackbar.isShown()) {
			this.snackbar.setText(message);
			this.snackbar.setDuration(snackDuration);
		} else {
			this.snackbar = Snackbar.make(this.view, message, snackDuration);
		}
		this.snackbar.setAction(actionMessage, actionListener);
		this.snackbar.show();
	}

	public void dismiss() {
		if (this.snackbar != null) {
			this.snackbar.dismiss();
		}
	}
}
