package com.projects.ims.authentication.service;

import com.projects.ims.authentication.dto.request.LoginRequest;
import com.projects.ims.authentication.dto.request.RegisterRequest;
import com.projects.ims.authentication.dto.response.AuthResponse;
import com.projects.ims.authentication.security.CustomUserDetails;
import com.projects.ims.authentication.security.JwtService;
import com.projects.ims.authentication.service.implementation.AuthenticationService;
import com.projects.ims.entity.Role;
import com.projects.ims.entity.User;
import com.projects.ims.exception.ResourceAlreadyExistsException;
import com.projects.ims.repository.RoleRepository;
import com.projects.ims.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final  JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public  AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
        user.setRole(new Role());
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserNameOrEmail(),
                        request.getPassword()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .roleName(userDetails.getRole().getRoleName())
                .tokenType("Bearer")
                .message("Login Successful")
                .build();


    }


}
