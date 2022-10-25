package com.georgeracu.client.exception;

public final class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }
}
