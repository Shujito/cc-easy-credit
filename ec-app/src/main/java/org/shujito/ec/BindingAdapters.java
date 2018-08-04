package org.shujito.ec;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * @author shujito, 8/4/18
 */
public class BindingAdapters {
	@BindingAdapter("android:src")
	public static void setImageResource(ImageView imageView, @DrawableRes int resource) {
		imageView.setImageResource(resource);
	}
}
