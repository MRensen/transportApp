package com.MRensen.transportApp.exception;

import java.io.Serial;

public class AttributeOverrideException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public AttributeOverrideException() {
        super();
    }

    public AttributeOverrideException(String message) {
        super(message);
    }
}
