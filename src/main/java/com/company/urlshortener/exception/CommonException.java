package com.company.urlshortener.exception;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {
    private final HttpStatus status;

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
