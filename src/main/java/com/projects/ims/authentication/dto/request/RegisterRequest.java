package com.projects.ims.authentication.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String name;
    private String password;
    private String email;
    private String contact;
}
