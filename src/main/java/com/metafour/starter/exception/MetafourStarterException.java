package com.metafour.starter.exception;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
public class MetafourStarterException extends Exception {

	private static final long serialVersionUID = -901985702074894331L;

	public MetafourStarterException() {
		super();
	}

	public MetafourStarterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MetafourStarterException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetafourStarterException(String message) {
		super(message);
	}

	public MetafourStarterException(Throwable cause) {
		super(cause);
	}
}
