package com.projects.ims.authentication.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userNameOrEmail;
    private String password;
}
