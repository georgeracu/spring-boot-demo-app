package com.georgeracu.demo.springboot.port.out.client;

public final class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }
}
