package org.shujito.ec.credit.model;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * @author shujito, 8/3/18
 */
@Data
public class CreditStatusType {
	@Expose
	private int id;
	@Expose
	private String description;
}
