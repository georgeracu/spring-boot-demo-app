package com.georgeracu.client.exception;

public final class NonRetryableException extends RuntimeException {

    public NonRetryableException(String message) {
        super(message);
    }
}
