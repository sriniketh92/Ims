package com.projects.ims.authentication.service;

import com.projects.ims.authentication.dto.request.LoginRequest;
import com.projects.ims.authentication.dto.request.RegisterRequest;
import com.projects.ims.authentication.dto.response.AuthResponse;
import com.projects.ims.authentication.service.implementation.AuthenticationService;
import com.projects.ims.entity.User;
import com.projects.ims.exception.ResourceAlreadyExistsException;
import com.projects.ims.repository.RoleRepository;
import com.projects.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public  AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new ResourceAlreadyExistsException("Email already registered");
        if(userRepository.existsByContact(request.getContact()))
            throw new ResourceAlreadyExistsException("Contact already registered");
        if(userRepository.existsByUserName(request.getUsername()))
            throw new ResourceAlreadyExistsException("Username already registered");
        User user = new User();
        user.setUserName(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setContact(request.getContact());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(null);
        userRepository.save(user);
    }
    public AuthResponse login(LoginRequest request){

        return new AuthResponse();
    }


}
