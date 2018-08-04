package org.shujito.ec.main.pages;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.shujito.ec.R;
import org.shujito.ec.databinding.CreditRequestItemBinding;
import org.shujito.ec.network.CreditRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shujito, 7/28/18
 */
public class CreditRequestsAdapter extends RecyclerView.Adapter<CreditRequestsAdapter.CreditRequestItem> {
	private final List<CreditRequest> data = new ArrayList<>();

	public class CreditRequestItem extends RecyclerView.ViewHolder {
		public final CreditRequestItemBinding binding;

		public CreditRequestItem(ViewGroup viewGroup) {
			this(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.credit_request_item, viewGroup, false));
		}

		private CreditRequestItem(CreditRequestItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
		}
	}

	@NonNull
	@Override
	public CreditRequestItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new CreditRequestItem(parent);
	}

	@Override
	public void onBindViewHolder(@NonNull CreditRequestItem holder, int position) {
		CreditRequest creditRequest = this.data.get(position);
		holder.binding.setCreditRequest(creditRequest);
	}

	@Override
	public int getItemCount() {
		return this.data.size();
	}

	public void datasource(List<CreditRequest> data) {
		this.data.clear();
		this.data.addAll(data);
		this.notifyDataSetChanged();
	}
}
