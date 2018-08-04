package org.shujito.ec.network;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.shujito.ec.R;

import lombok.Data;

/**
 * @author shujito, 8/4/18
 */
@Data
public class CreditRequest {
	public static final String PENDING = "Pendiente";
	public static final String ACCEPTED = "Aceptada";
	public static final String REJECTED = "Rechazada";

	public enum Status {
		@SerializedName(PENDING)
		Pending(PENDING, R.drawable.ic_info),
		@SerializedName(ACCEPTED)
		Accepted(ACCEPTED, R.drawable.ic_check_circle),
		@SerializedName(REJECTED)
		Rejected(REJECTED, R.drawable.ic_cancel),
		//
		;
		public final String label;
		public final int drawable;

		Status(String label, int drawable) {
			this.label = label;
			this.drawable = drawable;
		}
	}

	public enum Month {
		@SerializedName("3") _3(R.drawable.ic_numeric_3_box_outline),
		@SerializedName("6") _6(R.drawable.ic_numeric_6_box_outline),
		@SerializedName("9") _9(R.drawable.ic_numeric_9_box_outline),
		//
		;
		public final int drawable;

		Month(@DrawableRes int drawable) {
			this.drawable = drawable;
		}
	}

	@Expose
	Double amount;
	@Expose
	Month months;
	@Expose
	Double interest;
	@Expose
	Status status;
	@Expose
	Double total;
}
