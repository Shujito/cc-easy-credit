package org.shujito.ec.credit.model;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * @author shujito, 8/4/18
 */
@Data
public class PendingCreditRequest {
	@Expose
	Double amount;
	@Expose
	Integer months;
	@Expose
	Double interest;
	@Expose
	String status;
	@Expose
	Double total;
}
