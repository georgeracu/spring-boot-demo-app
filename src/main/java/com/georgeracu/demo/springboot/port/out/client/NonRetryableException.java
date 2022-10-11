package com.georgeracu.demo.springboot.port.out.client;

public final class NonRetryableException extends RuntimeException {

    public NonRetryableException(String message) {
        super(message);
    }
}
