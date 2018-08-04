package org.shujito.ec.network;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.StringRes;

import org.shujito.ec.BR;
import org.shujito.ec.R;

import lombok.ToString;

/**
 * @author shujito, 8/4/18
 */
@ToString
public class CreditRequestForm extends BaseObservable {
	private String amount;
	private boolean hasCreditCard;
	private int paymentType;

	@Bindable
	public String getAmount() {
		return this.amount;
	}

	@Bindable
	public boolean isHasCreditCard() {
		return this.hasCreditCard;
	}

	@Bindable
	public int getPaymentType() {
		return paymentType;
	}

	public void setHasCreditCard(boolean hasCreditCard) {
		this.hasCreditCard = hasCreditCard;
		this.notifyPropertyChanged(BR.hasCreditCard);
	}

	public void setAmount(String amount) {
		this.amount = amount;
		this.notifyPropertyChanged(BR.amount);
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
		this.notifyPropertyChanged(BR.paymentType);
	}

	@StringRes
	public int validate() {
		if (this.amount == null || this.amount.isEmpty()) {
			return R.string.enter_amount;
		}
		if (this.paymentType == 0) {
			return R.string.select_payment_type;
		}
		return 0;
	}
}
