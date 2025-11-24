package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.mapper.GetRoleResponseMapper;
import com.foodcourt.users_service.application.mapper.GetUserByEmailResponseMapper;
import com.foodcourt.users_service.application.mapper.GetUserResponseMapper;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.IAuthServicePort;
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
    private final GetUserResponseMapper getUserResponseMapper;
    private final GetUserByEmailResponseMapper getUserByEmailResponseMapper;

    @Override
    public GetRoleResponse getRoleById(Long userId) {
        Role role = authServicePort.getUserRoleById(userId);
        return getRolResponseMapper.toResponse(role);
    }


    @Override
    public AuthResponse login(LoginCommand command){

        User userDomain = authServicePort.getUserByEmail(command.getEmail());
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

    @Override
    public GetUserByIdResponse getUserById(Long userId) {
        Owner person = authServicePort.getUserById(userId);
        Role role = authServicePort.getUserRoleById(userId);
        return getUserResponseMapper.toResponse(person, role);
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(String email) {
        User user = authServicePort.getUserByEmail(email);
        return getUserByEmailResponseMapper.toResponse(user);
    }

}


