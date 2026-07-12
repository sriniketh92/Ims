package com.projects.ims.authentication.service.implementation;

import com.projects.ims.authentication.dto.request.LoginRequest;
import com.projects.ims.authentication.dto.request.RegisterRequest;
import com.projects.ims.authentication.dto.response.AuthResponse;

public interface AuthenticationService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
