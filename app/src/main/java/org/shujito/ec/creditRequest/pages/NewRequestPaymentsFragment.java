package org.shujito.ec.creditRequest.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shujito.ec.KeyboardHelpers;
import org.shujito.ec.R;
import org.shujito.ec.databinding.NewRequestPaymentsBinding;

/**
 * @author shujito, 8/2/18
 */
public class NewRequestPaymentsFragment extends Fragment {
	private NewRequestPaymentsBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.new_request_payments, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && this.binding != null) {
			this.binding.root.post(() -> KeyboardHelpers.hide(this.binding.root));
		}
	}
}
