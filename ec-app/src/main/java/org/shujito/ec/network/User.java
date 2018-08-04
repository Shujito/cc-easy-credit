package org.shujito.ec.network;

import com.google.gson.annotations.Expose;

import org.parceler.Parcel;

import lombok.Data;

/**
 * @author shujito, 8/3/18
 */
@Data
@Parcel
public class User {
	public static final String TAG = User.class.getName();
	@Expose
	private int id;
	@Expose
	private String username;
	@Expose
	private int age;
}
