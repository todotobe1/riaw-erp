package com.riawworks.riaw.erp.framework.exception;

public class BeanConversionException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public BeanConversionException() {
		super();
	}

	public BeanConversionException(String message) {
		super(message);
	}

	public BeanConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanConversionException(Throwable cause) {
		super(cause);
	}

}
