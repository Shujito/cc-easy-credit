package org.shujito.ec.main.pages;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.parceler.Parcels;
import org.shujito.ec.R;
import org.shujito.ec.databinding.RequestBinding;
import org.shujito.ec.network.CreditRequest;
import org.shujito.ec.network.EasyCreditApi;
import org.shujito.ec.network.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shujito, 7/28/18
 */
public class RequestFragment extends RxFragment {
	private RequestBinding binding;
	private CreditRequestsAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, R.layout.request, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
		this.binding.recyclerView.setAdapter(this.adapter = new CreditRequestsAdapter());
	}

	@Override
	public void onResume() {
		super.onResume();
		Bundle arguments = this.getArguments();
		if (arguments == null) {
			return;
		}
		User user = Parcels.unwrap(arguments.getParcelable(User.TAG));
		EasyCreditApi.INSTANCE.getCreditRequests(user.getId(), CreditRequest.ACCEPTED)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.compose(this.bindToLifecycle())
			.subscribe((data, error) -> {
				if (error != null) {
					Toast.makeText(this.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
					return;
				}
				this.binding.setData(data);
				this.adapter.datasource(data);
			});
	}
}
