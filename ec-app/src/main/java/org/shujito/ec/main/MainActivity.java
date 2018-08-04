package org.shujito.ec.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.parceler.Parcels;
import org.shujito.ec.FragmentHelpers;
import org.shujito.ec.R;
import org.shujito.ec.creditRequest.NewRequestActivity;
import org.shujito.ec.databinding.MainBinding;
import org.shujito.ec.model.User;

/**
 * @author shujito, 7/28/18
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
	private MainBinding binding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		User user = Parcels.unwrap(this.getIntent().getParcelableExtra(User.TAG));
		this.binding = DataBindingUtil.setContentView(this, R.layout.main);
		this.binding.setUser(user);
		this.setSupportActionBar(this.binding.toolbar);
		this.binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
		this.binding.bottomNavigation.setOnNavigationItemReselectedListener(this);
		this.binding.floatingActionButton.setOnClickListener(v -> {
			Intent intent = new Intent(this, NewRequestActivity.class);
			intent.putExtra(User.TAG, Parcels.wrap(user));
			this.startActivity(intent);
		});
		if (savedInstanceState == null) {
			this.binding.floatingActionButton.post(() -> this.binding.floatingActionButton.hide());
			FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new ProfileFragment(), false);
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		Fragment fragment = null;
		switch (item.getItemId()) {
			case R.id.menu_profile: {
				fragment = new ProfileFragment();
				this.binding.floatingActionButton.hide();
			}
			break;
			case R.id.menu_history: {
				fragment = new HistoryFragment();
				this.binding.floatingActionButton.hide();
			}
			break;
			case R.id.menu_requests: {
				fragment = new RequestFragment();
				this.binding.floatingActionButton.show();
			}
			break;
		}
		if (fragment == null) {
			return true;
		}
		Bundle bundle = new Bundle();
		bundle.putParcelable(User.TAG, Parcels.wrap(this.binding.getUser()));
		fragment.setArguments(bundle);
		FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, fragment, false);
		return true;
	}

	@Override
	public void onNavigationItemReselected(@NonNull MenuItem item) {
	}
}
