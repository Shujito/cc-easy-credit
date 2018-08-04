package org.shujito.ec.creditRequest.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.shujito.ec.KeyboardHelpers;
import org.shujito.ec.R;
import org.shujito.ec.creditRequest.NewRequestActivity;
import org.shujito.ec.databinding.NewRequestAmountBinding;
import org.shujito.ec.model.CreditRequest;
import org.shujito.ec.util.NumberUtils;

/**
 * @author shujito, 8/2/18
 */
public class NewRequestAmountFragment extends RxFragment {
	private static final String TAG = NewRequestAmountFragment.class.getSimpleName();
	private NewRequestAmountBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.new_request_amount, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
//		CreditRequest creditRequest = NewRequestActivity.getCreditRequest(this.getActivity());

//		RxTextView.textChanges(this.binding.amount)
//			.map(Object::toString)
//			.map(NumberUtils::parseInt)
//			.compose(this.bindToLifecycle())
//			.subscribe(creditRequest::setAmount);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && this.binding != null) {
			Log.i(TAG, "setUserVisibleHint");
			this.binding.root.post(() -> KeyboardHelpers.hide(this.binding.root));
		}
	}
}
