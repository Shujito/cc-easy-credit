package org.shujito.ec.util;

import com.google.gson.JsonSyntaxException;

import org.eclipse.jetty.http.HttpStatus;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import ro.pippo.core.Application;
import ro.pippo.core.DefaultErrorHandler;
import ro.pippo.core.RuntimeMode;
import ro.pippo.core.route.RouteContext;

/**
 * @author shujito, 8/3/18
 */
public class CustomErrorHandler extends DefaultErrorHandler {
	public CustomErrorHandler(Application application) {
		super(application);
		this.setExceptionHandler(ApiException.class, (e, rc) -> {
			ApiResponse response = ((ApiException) e).getApiResponse();
			rc.status(response.getStatus()).json().send(response);
		});
		this.setExceptionHandler(Exception.class, (e, rc) -> {
			e.printStackTrace();
			String stackTrace = null;
			if (application.getRuntimeMode() == RuntimeMode.DEV) {
				StringWriter stringWriter = new StringWriter();
				try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
					e.printStackTrace(printWriter);
				}
				stackTrace = stringWriter.toString();
			}
			String message = null;
			Integer status = null;
			if (e instanceof JsonSyntaxException ||
				(e.getMessage() != null && e.getMessage().matches("\\w+ is null"))) {
				status = HttpStatus.NOT_ACCEPTABLE_406;
			}
			if (e instanceof UnableToExecuteStatementException && e.getCause() != null) {
				status = HttpStatus.NOT_ACCEPTABLE_406;
				message = e.getCause().getMessage();
			}
			message = Optional.ofNullable(message).orElse(e.getMessage());
			status = Optional.ofNullable(status).orElse(HttpStatus.INTERNAL_SERVER_ERROR_500);
			rc.status(status).json().send(ApiResponse.builder()
				.status(status)
				.message(message)
				.stackTrace(stackTrace)
				.build());
		});
	}

	@Override
	public void handle(int statusCode, RouteContext routeContext) {
		routeContext.status(statusCode).json().send(ApiResponse.builder()
			.status(statusCode)
			.message("Not found")
			.build());
	}
}
