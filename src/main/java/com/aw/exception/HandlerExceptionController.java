package com.aw.exception;

import com.aw.utils.KeyResponseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class HandlerExceptionController {

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), Objects.nonNull(e.getCause()) ? e.getCause() : e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(KeyResponseProperties.ERROR, e.getErrors()));
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), Objects.nonNull(e.getCause()) ? e.getCause() : e);
        return ResponseEntity
                .status(Objects.nonNull(e.getStatus()) ? e.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
