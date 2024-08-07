package com.aw.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountResponse {

    private String username;

    private String email;

    private String phone;

    private String project;

}
