package com.aw.service;

import com.aw.request.AccountRequest;
import com.aw.response.AccountResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<AccountResponse> execute(AccountRequest request);

}
