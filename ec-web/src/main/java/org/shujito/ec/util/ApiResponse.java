package org.shujito.ec.util;

import com.google.gson.annotations.Expose;

import lombok.Builder;
import lombok.Data;

/**
 * @author shujito, 8/3/18
 */
@Data
@Builder
public class ApiResponse<T> {
	@Expose
	private final boolean success;
	@Expose
	private final Integer status;
	@Expose
	private final String message;
	@Expose
	private final T data;
	@Expose
	private final String stackTrace;
}
