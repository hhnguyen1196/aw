package com.validationdemo.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

@Data
public class ResponseUtils {

    private HttpStatus status;

    private Object body;

    public static ResponseEntity<Object> ok(Object body) {
        return ResponseEntity.ok().body(Collections.singletonMap(KeyResponseProperties.BODY, body));
    }

    public static ResponseEntity<Object> statusCode(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(Collections.singletonMap(KeyResponseProperties.BODY, body));
    }
}
