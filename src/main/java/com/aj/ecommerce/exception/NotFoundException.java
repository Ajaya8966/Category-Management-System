package com.aj.ecommerce.exception;

public class NotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4539160650040769372L;

	public NotFoundException(String message) {
        super(message);
    }
}
