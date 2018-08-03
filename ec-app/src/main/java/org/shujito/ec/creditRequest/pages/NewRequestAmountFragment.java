package org.shujito.ec.creditRequest.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shujito.ec.KeyboardHelpers;
import org.shujito.ec.R;
import org.shujito.ec.databinding.NewRequestAmountBinding;

/**
 * @author shujito, 8/2/18
 */
public class NewRequestAmountFragment extends Fragment {
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
