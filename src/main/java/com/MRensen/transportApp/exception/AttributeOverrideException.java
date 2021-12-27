package com.MRensen.transportApp.exception;

public class AttributeOverrideException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AttributeOverrideException() {
        super();
    }

    public AttributeOverrideException(String message) {
        super(message);
    }
}
