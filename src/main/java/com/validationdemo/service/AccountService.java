package com.validationdemo.service;

import com.validationdemo.dto.AccountRequest;
import com.validationdemo.exception.ValidationException;
import com.validationdemo.utils.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final Validate validate;

    public void execute(AccountRequest request) {
        Map<String, List<String>> errors = validate.validate(request);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ValidationException(errors);
        }

    }
}
