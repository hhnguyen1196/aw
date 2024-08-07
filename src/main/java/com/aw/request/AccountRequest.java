package com.aw.request;

import com.aw.annotation.Username;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {

    @Username
    @NotNull(message = "hello_world")
    private String username;

    private String password;

    private String email;

    private String phone;

    private String project;

}
