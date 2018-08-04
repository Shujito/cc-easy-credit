package org.shujito.ec.creditRequest.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.shujito.ec.KeyboardHelpers;
import org.shujito.ec.R;
import org.shujito.ec.creditRequest.NewRequestActivity;
import org.shujito.ec.databinding.NewRequestHasCreditCardBinding;
import org.shujito.ec.model.CreditRequest;

/**
 * @author shujito, 8/2/18
 */
public class NewRequestHasCreditCardFragment extends RxFragment {
	private NewRequestHasCreditCardBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.new_request_has_credit_card, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
//		CreditRequest creditRequest = NewRequestActivity.getCreditRequest(this.getActivity());
//		RxCompoundButton.checkedChanges(this.binding.creditCardSwitch)
//			.compose(this.bindToLifecycle())
//			.subscribe(creditRequest::setHasCreditCard);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && this.binding != null) {
			this.binding.root.post(() -> KeyboardHelpers.hide(this.binding.root));
		}
	}
}
