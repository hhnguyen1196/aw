package com.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private Map<String, List<String>> errors;
}
