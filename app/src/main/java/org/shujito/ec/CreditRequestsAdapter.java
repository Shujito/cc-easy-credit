package org.shujito.ec;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author shujito, 7/28/18
 */
public class CreditRequestsAdapter extends RecyclerView.Adapter<CreditRequestsAdapter.CreditRequestItem> {
	public class CreditRequestItem extends RecyclerView.ViewHolder {
		public CreditRequestItem(ViewGroup viewGroup) {
			this(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.credit_request_item, viewGroup, false));
		}

		private CreditRequestItem(ViewDataBinding binding) {
			super(binding.getRoot());
		}
	}

	@NonNull
	@Override
	public CreditRequestItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new CreditRequestItem(parent);
	}

	@Override
	public void onBindViewHolder(@NonNull CreditRequestItem holder, int position) {
	}

	@Override
	public int getItemCount() {
		return 10;
	}
}
