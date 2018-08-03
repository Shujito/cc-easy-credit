package org.shujito.ec.creditRequest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.shujito.ec.R;
import org.shujito.ec.databinding.NewRequestBinding;

import java.util.Objects;

/**
 * @author shujito, 7/28/18
 */
public class NewRequestActivity extends AppCompatActivity implements View.OnClickListener {
	private NewRequestBinding binding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.binding = DataBindingUtil.setContentView(this, R.layout.new_request);
		this.setSupportActionBar(this.binding.toolbar);
		this.binding.viewPager.setAdapter(new NewRequestPagerAdapter(this.getSupportFragmentManager()));
		this.binding.floatingActionButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int currentItem = this.binding.viewPager.getCurrentItem();
		int count = Objects.requireNonNull(this.binding.viewPager.getAdapter()).getCount();
		if (currentItem >= count - 1) {
			this.finish();
		} else {
			this.binding.viewPager.setCurrentItem(currentItem + 1);
		}
	}

	@Override
	public void onBackPressed() {
		int currentItem = this.binding.viewPager.getCurrentItem();
		if (currentItem <= 0) {
			super.onBackPressed();
		} else {
			this.binding.viewPager.setCurrentItem(currentItem - 1);
		}
	}
}
