package org.shujito.ec.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Utilities for the keyboard
 *
 * @author Alberto Ramos
 */
@SuppressWarnings("unused")
public final class KeyboardHelpers {
	private static final String TAG = KeyboardHelpers.class.getSimpleName();

	private KeyboardHelpers() {
		throw new RuntimeException("don't!");
	}

	/**
	 * Displays the keyboard
	 *
	 * @param view to gather a window token from
	 * @return whether the view took focus or not
	 */
	public static boolean show(View view) {
		InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean showSoftInput = false;
		if (imm != null) {
			showSoftInput = imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
		return showSoftInput && view.requestFocus();
	}

	/**
	 * Conceals the keyboard
	 *
	 * @param view to gather a window token from
	 */
	public static boolean hide(View view) {
		if (view == null) {
			Log.w(TAG, "hide: view == null");
			return false;
		}
		InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		view.clearFocus();
		return imm != null && imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static Observable<Boolean> watchKeyboard(final Activity a, final View v) {
		return RxView.globalLayouts(v)
			.map(o -> a)
			.flatMapSingle(activity -> Single.zip(
				Single.just(activity)
					.map(Activity::getWindowManager)
					.map(WindowManager::getDefaultDisplay),
				Single.just(v),
				(windowManager, root) -> {
					Point screenSize = new Point();
					Rect windowSize = new Rect();
					windowManager.getSize(screenSize);
					root.getWindowVisibleDisplayFrame(windowSize);
//					Log.i(TAG, "watchKeyboard, screenSize.y:" + screenSize.y);
//					Log.i(TAG, "watchKeyboard, windowSize.bottom:" + windowSize.bottom);
					return windowSize.bottom == screenSize.y;
				}
			));
	}
}
