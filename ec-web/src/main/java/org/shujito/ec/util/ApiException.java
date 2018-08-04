package org.shujito.ec.util;

import lombok.Getter;
import ro.pippo.core.PippoRuntimeException;

/**
 * @author shujito, 8/3/18
 */
class ApiException extends PippoRuntimeException {
	private static final long serialVersionUID = 1L;
	@Getter
	private final ApiResponse apiResponse;

	public ApiException(int status) {
		this.apiResponse = ApiResponse.builder().status(status).build();
	}

	public ApiException(int status, Exception exception) {
		super(exception);
		this.apiResponse = ApiResponse.builder().status(status).message(exception.getMessage()).build();
	}

	public ApiException(int status, String message) {
		super(message);
		this.apiResponse = ApiResponse.builder().status(status).message(message).build();
	}
}
