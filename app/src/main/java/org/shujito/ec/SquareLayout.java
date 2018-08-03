package org.shujito.ec;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author shujito, 8/2/18
 */
public class SquareLayout extends RelativeLayout {
	public SquareLayout(Context context) {
		super(context);
	}

	public SquareLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	@SuppressWarnings("SuspiciousNameCombination")
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}
