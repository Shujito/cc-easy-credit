package org.shujito.ec.main.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shujito.ec.R;
import org.shujito.ec.databinding.HistoryBinding;

/**
 * @author shujito, 7/28/18
 */
public class HistoryFragment extends Fragment {
	private HistoryBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.history, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
		this.binding.recyclerView.setAdapter(new CreditRequestsAdapter());
	}
}