package com.foodcourt.users_service.application.handler;

import com.foodcourt.users_service.application.dto.CreateOwnerCommand;
import com.foodcourt.users_service.application.dto.GetRoleResponse;
import com.foodcourt.users_service.application.dto.OwnerResponse;
import com.foodcourt.users_service.application.mapper.CreateOwnerCommandMapper;
import com.foodcourt.users_service.application.mapper.GetRoleResponseMapper;
import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.api.IAuthServicePort;
import com.foodcourt.users_service.domain.port.api.IOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional

public class AuthHandler implements IAuthHandler {

    private final IAuthServicePort authServicePort;

    private final GetRoleResponseMapper getRolResponseMapper;


    @Override
    public GetRoleResponse getRoleById(Long userId) {

        Role role = authServicePort.getUserRoleById(userId);
        return getRolResponseMapper.toResponse(role);

    }
}
