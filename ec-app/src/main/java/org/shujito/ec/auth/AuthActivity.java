package org.shujito.ec.auth;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.parceler.Parcels;
import org.shujito.ec.R;
import org.shujito.ec.databinding.AuthBinding;
import org.shujito.ec.main.MainActivity;
import org.shujito.ec.network.EasyCreditApi;
import org.shujito.ec.network.User;
import org.shujito.ec.util.KeyboardHelpers;
import org.shujito.ec.util.NumberUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shujito, 8/3/18
 */
public class AuthActivity extends RxAppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AuthBinding binding = DataBindingUtil.setContentView(this, R.layout.auth);
		binding.access.setOnClickListener(v -> {
			KeyboardHelpers.hide(v);
			EasyCreditApi.INSTANCE.user(binding.getUsername(), binding.getAge())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(this.bindUntilEvent(ActivityEvent.PAUSE))
				.subscribe((user, error) -> {
					if (error != null) {
						Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
						return;
					}
					Intent intent = new Intent(this, MainActivity.class);
					intent.putExtra(User.TAG, Parcels.wrap(user));
					this.startActivity(intent);
					this.finish();
				});
		});
		Observable.combineLatest(
			RxTextView.textChanges(binding.username),
			RxTextView.textChanges(binding.age)
				.map(Object::toString)
				.map(NumberUtils::parseInt),
			(username, age) -> {
				boolean usernameCheck = username.length() > 2;
				boolean ageCheck = age >= 20;
				if (!usernameCheck && username.length() > 0) {
					binding.usernameLabel.setError("Al menos dos caracteres");
				} else {
					binding.usernameLabel.setError(null);
				}
				if (!ageCheck && age > 0) {
					binding.ageLabel.setError("No menores de 20");
				} else {
					binding.ageLabel.setError(null);
				}
				return usernameCheck && ageCheck;
			}
		)
			.compose(this.bindToLifecycle())
			.subscribe(binding.access::setEnabled);
	}
}
