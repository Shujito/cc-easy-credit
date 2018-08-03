package org.shujito.ec.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.shujito.ec.FragmentHelpers;
import org.shujito.ec.creditRequest.NewRequestActivity;
import org.shujito.ec.R;
import org.shujito.ec.databinding.MainBinding;

/**
 * @author shujito, 7/28/18
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
	private MainBinding binding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.binding = DataBindingUtil.setContentView(this, R.layout.main);
		this.setSupportActionBar(this.binding.toolbar);
		this.binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
		this.binding.bottomNavigation.setOnNavigationItemReselectedListener(this);
		this.binding.floatingActionButton.setOnClickListener(v -> {
			//
			 this.startActivity(new Intent(this, NewRequestActivity.class));
		});
		if (savedInstanceState == null) {
			this.binding.floatingActionButton.post(() -> this.binding.floatingActionButton.hide());
			FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new ProfileFragment(), false);
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_profile: {
				FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new ProfileFragment(), false);
				this.binding.floatingActionButton.hide();
			}
			break;
			case R.id.menu_history: {
				FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new HistoryFragment(), false);
				this.binding.floatingActionButton.hide();
			}
			break;
			case R.id.menu_requests: {
				FragmentHelpers.show(this.getSupportFragmentManager(), R.id.content, new RequestFragment(), false);
				this.binding.floatingActionButton.show();
			}
			break;
		}
		return true;
	}

	@Override
	public void onNavigationItemReselected(@NonNull MenuItem item) {
	}
}
