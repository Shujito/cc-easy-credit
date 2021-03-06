package org.shujito.ec.user;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * @author shujito, 8/3/18
 */
@Data
public class User {
	@Expose
	private int id;
	@Expose
	private String username;
	@Expose
	private int age;
}
