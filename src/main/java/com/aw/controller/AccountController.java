package com.aw.controller;

import com.aw.request.AccountRequest;
import com.aw.response.AccountResponse;
import com.aw.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/login")
    public ResponseEntity<AccountResponse> request(@RequestBody AccountRequest request) {
        return service.execute(request);
    }

}
