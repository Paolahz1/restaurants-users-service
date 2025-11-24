package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Person;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.api.IAuthServicePort;
import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;

public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String rawPassword) {
        return "";
    }

    @Override
    public Role getUserRoleById(Long userId) {

        //todo validar que exista user
        Role role = authPersistencePort.getRoleById(userId);
        return  role;

    }

    @Override
    public Owner getUserById(Long userId) {
        return authPersistencePort.getUserById(userId);
    }

}

