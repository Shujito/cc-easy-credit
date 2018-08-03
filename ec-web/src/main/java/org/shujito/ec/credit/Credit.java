package org.shujito.ec.credit;

import lombok.Data;

/**
 * @author shujito, 8/3/18
 */
@Data
public class Credit {
	private int id;
	private int userId;
	private double amount;
	private boolean approved;
	private int paymentTypeId;
}
