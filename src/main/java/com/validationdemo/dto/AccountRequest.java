package com.validationdemo.dto;

import com.validationdemo.annotation.Username;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {

    @Username
    @NotNull(message = "hello_world")
    private String username;

    private String password;

    private String email;

    private String phone;
}
