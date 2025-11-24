package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.*;
import com.foodcourt.users_service.application.mapper.CreateOwnerCommandMapper;
import com.foodcourt.users_service.application.mapper.GetRoleResponseMapper;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.api.IAuthServicePort;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;
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

    private final IAuthServicePort authServicePort;
    private final GetRoleResponseMapper getRolResponseMapper;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    public GetRoleResponse getRoleById(Long userId) {
        Role role = authServicePort.getUserRoleById(userId);
        return getRolResponseMapper.toResponse(role);
    }


    @Override
    public AuthResponse login(LoginCommand command){

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getEmail(),
                        command.getPassword()
                )
        );

        UserDetails user = userDetailsService.loadUserByUsername(command.getEmail());

        String jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);

    }
}


