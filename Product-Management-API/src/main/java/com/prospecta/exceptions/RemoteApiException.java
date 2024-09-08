package com.prospecta.exceptions;

public class RemoteApiException extends RuntimeException {

	public RemoteApiException() {
		super();
	}

	public RemoteApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RemoteApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public RemoteApiException(String message) {
		super(message);
	}

	public RemoteApiException(Throwable cause) {
		super(cause);
	}

}
