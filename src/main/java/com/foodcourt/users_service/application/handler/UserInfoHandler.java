package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.get.GetRoleResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByEmailResponse;
import com.foodcourt.users_service.application.dto.get.GetUserByIdResponse;
import com.foodcourt.users_service.application.dto.login.AuthResponse;
import com.foodcourt.users_service.application.dto.login.LoginCommand;
import com.foodcourt.users_service.application.mapper.GetRoleResponseMapper;
import com.foodcourt.users_service.application.mapper.GetUserByEmailResponseMapper;
import com.foodcourt.users_service.application.mapper.GetUserByIdlResponseMapper;
import com.foodcourt.users_service.domain.model.Role;
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

public class UserInfoHandler implements IUserInfoHandler {

    private final IUserGetInfoServicePort userGetInfoServicePort;
    private final GetRoleResponseMapper getRolResponseMapper;
    private final GetUserByEmailResponseMapper getUserByEmailResponseMapper;
    private final GetUserByIdlResponseMapper getUserResponseMapper;

    @Override
    public GetRoleResponse getRoleById(Long userId) {
        Role role = userGetInfoServicePort.getUserRoleById(userId);
        return getRolResponseMapper.toResponse(role);
    }

    @Override
    public GetUserByIdResponse getUserById(Long userId) {
        User user = userGetInfoServicePort.getUserById(userId);
        return getUserResponseMapper.toResponse(user);
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(String email) {
        User user = userGetInfoServicePort.getUserByEmail(email);
        return getUserByEmailResponseMapper.toResponse(user);
    }


}


