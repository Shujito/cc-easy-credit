package org.shujito.ec.creditRequest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.parceler.Parcels;
import org.shujito.ec.network.EasyCreditApi;
import org.shujito.ec.util.KeyboardHelpers;
import org.shujito.ec.R;
import org.shujito.ec.util.SnackbarWrapper;
import org.shujito.ec.databinding.NewRequestBinding;
import org.shujito.ec.network.CreditRequest;
import org.shujito.ec.network.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shujito, 7/28/18
 */
public class NewRequestActivity extends RxAppCompatActivity implements View.OnClickListener {
	private static final String TAG = NewRequestActivity.class.getSimpleName();
	private SnackbarWrapper snackbarWrapper;
	private NewRequestBinding binding;
	private User user;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.user = Parcels.unwrap(this.getIntent().getParcelableExtra(User.TAG));
		this.binding = DataBindingUtil.setContentView(this, R.layout.new_request);
		CreditRequest creditRequest = new CreditRequest();
		this.binding.setCreditRequest(creditRequest);
		this.setSupportActionBar(this.binding.toolbar);
		this.binding.floatingActionButton.setOnClickListener(this);
		this.snackbarWrapper = new SnackbarWrapper(this.binding.coordinatorLayout);
	}

	@Override
	public void onClick(View v) {
		CreditRequest creditRequest = this.binding.getCreditRequest();
		if (creditRequest == null) {
			return;
		}
		int validate = creditRequest.validate();
		if (validate != 0) {
			this.snackbarWrapper.show(validate, Toast.LENGTH_SHORT);
			return;
		} else {
			this.snackbarWrapper.dismiss();
		}
		KeyboardHelpers.hide(v);
		Log.i(TAG, "onClick: " + creditRequest);
		EasyCreditApi.INSTANCE.credit(
			this.user.getId(),
			creditRequest.getAmount(),
			creditRequest.getPaymentType()
		)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.compose(this.bindUntilEvent(ActivityEvent.PAUSE))
			.subscribe((credit, error) -> {
				if (error != null) {
					this.snackbarWrapper.show(error.getMessage(), Snackbar.LENGTH_LONG);
					return;
				}
				this.finish();
			});
	}
}
