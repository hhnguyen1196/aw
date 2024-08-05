package com.validationdemo.controller;

import com.validationdemo.dto.AccountRequest;
import com.validationdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/login")
    public void request(@RequestBody AccountRequest request) {
        service.execute(request);
    }
}
