package com.aw.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
public class BusinessException extends RuntimeException {

    private HttpStatus status;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
