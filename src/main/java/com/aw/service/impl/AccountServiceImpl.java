package com.aw.service.impl;

import com.aw.request.AccountRequest;
import com.aw.exception.ValidationException;
import com.aw.properties.AWProperties;
import com.aw.response.AccountResponse;
import com.aw.service.AccountService;
import com.aw.utils.MessageSourceProperties;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final Validator validator;

    private final MessageSource messageSource;

    private final AWProperties properties;

    private static final String PASSWORD = "password";

    private static final List<String> prohibitedWords =
            Arrays.asList("đụ", "má", "dốt", "đần", "chó", "đcm", "đcmm", "buồi");

    public ResponseEntity<AccountResponse> execute(AccountRequest request) {

        log.info(request.toString());
        validate(request);

        AccountResponse.AccountResponseBuilder responseBuilder = AccountResponse.builder();

        responseBuilder
                .email("emailAW@gmail.com")
                .phone("phone")
                .username("032C051296")
                .project(properties.getVariables());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBuilder.build());
    }

    private void validate(AccountRequest request) {
        Map<String, List<String>> errors = validator.validate(request)
                .stream()
                .collect(Collectors.groupingBy(error -> error.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

        if (Objects.nonNull(request.getPassword()) && request.getPassword().length() < 8) {
            List<String> errorList = errors.get(PASSWORD) != null ? errors.get(PASSWORD) : new ArrayList<>();
            errorList.add(getMessage(MessageSourceProperties.PASSWORD_LENGTH_ERROR));
            errors.put(PASSWORD, errorList);
        }

        validateProhibitedWords(request.getUsername(), "username", errors);
        validateProhibitedWords(request.getEmail(), "email", errors);
        validateProhibitedWords(request.getPassword(), "password", errors);
        validateProhibitedWords(request.getPhone(), "phone", errors);

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationException(errors);
        }
    }

    private void validateProhibitedWords(String value, String field, Map<String, List<String>> errors) {

        if (!StringUtils.hasText(value)) {
            return;
        }

        List<String> keywordProhibitedWords = new ArrayList<>();
        prohibitedWords.stream()
                .filter(value::contains)
                .forEach(keywordProhibitedWords::add);

        if (!CollectionUtils.isEmpty(keywordProhibitedWords)) {
            String messageProhibitedWords = String.join(", ", keywordProhibitedWords);
            List<String> errorList = Objects.nonNull(errors.get(field)) ? errors.get(field) : new ArrayList<>();
            Object[] objects = new Object[]{messageProhibitedWords};
            errorList.add(messageSource.getMessage(MessageSourceProperties.PROHIBITED_WORD, objects, Locale.getDefault()));
            errors.put(field, errorList);
        }
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}
