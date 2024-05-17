package com.example.muslis.security;

import com.example.muslis.services.BasicUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final BasicUserDetailsService basicUserDetailsService;

    @Autowired
    public AuthProviderImpl(BasicUserDetailsService basicUserDetailsService) {
        this.basicUserDetailsService = basicUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails basicUserDetails = basicUserDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if (!password.equals(basicUserDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(basicUserDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
