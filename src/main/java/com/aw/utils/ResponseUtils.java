package com.aw.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseUtils <T> {

    private HttpStatus status;

    private T body;

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok().body(body);
    }

    public static <T> ResponseEntity<T> status(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }

}
