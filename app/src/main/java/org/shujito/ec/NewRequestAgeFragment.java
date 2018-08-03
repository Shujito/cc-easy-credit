package org.shujito.ec;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shujito.ec.databinding.NewRequestAgeBinding;

/**
 * @author shujito, 8/2/18
 */
public class NewRequestAgeFragment extends Fragment {
	private NewRequestAgeBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.new_request_age, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.binding.age.setMinValue(18);
		this.binding.age.setMaxValue(75);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && this.binding != null) {
			this.binding.root.post(() -> KeyboardHelpers.hide(this.binding.root));
		}
	}
}
