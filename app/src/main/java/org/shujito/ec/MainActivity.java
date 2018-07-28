package org.shujito.ec;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.shujito.ec.databinding.MainBinding;

/**
 * @author shujito, 7/28/18
 */
public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainBinding binding = DataBindingUtil.setContentView(this, R.layout.main);
		binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
			switch (item.getItemId()) {
				case R.id.menu_profile: {
					FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new ProfileFragment(), false);
				}
				break;
				case R.id.menu_history: {
					FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new HistoryFragment(), false);
				}
				break;
				case R.id.menu_requests: {
					FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new RequestFragment(), false);
				}
				break;
			}
			return true;
		});
	}
}
