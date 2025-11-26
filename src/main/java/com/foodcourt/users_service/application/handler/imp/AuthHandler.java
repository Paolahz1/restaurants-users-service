package com.foodcourt.users_service.application.handler.imp;

import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.handler.port.IAuthHandler;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.IUserGetInfoServicePort;
import com.foodcourt.users_service.infrastructure.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {

    private final IUserGetInfoServicePort userGetInfoServicePort;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse login(LoginCommand command){

        User userDomain = userGetInfoServicePort.getUserByEmail(command.getEmail());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getEmail(),
                        command.getPassword()
                )
        );

        UserDetails user = userDetailsService.loadUserByUsername(command.getEmail());
        String jwt = jwtService.generateToken(user, userDomain.getId(), userDomain.getRole().name());
        return new AuthResponse(jwt);

    }

}


