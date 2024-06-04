package com.example.muslis.services;

import com.example.muslis.entities.JwtAuthenticationResponse;
import com.example.muslis.entities.SignInRequest;
import com.example.muslis.entities.SignUpRequest;
import com.example.muslis.enums.Role;
import com.example.muslis.models.UserInfo;
import com.example.muslis.security.ActiveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final BasicUserDetailsService basicUserDetailsService;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserInfo.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Role.ROLE_LISTENER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(new ActiveUser(user));
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = basicUserDetailsService
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
