package com.demo.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Validate {

    private final Validator validator;

    public <T> Map<String, List<String>> validate(T request) {
        return validator.validate(request)
                .stream()
                .collect(Collectors.groupingBy(error -> error.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
    }
}
