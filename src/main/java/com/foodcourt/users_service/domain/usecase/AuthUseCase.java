package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.api.IAuthServicePort;

public class AuthUseCase implements IAuthServicePort {


    @Override
    public String login(String email, String rawPassword) {
        return "";
    }

    @Override
    public Role getUserRoleById(Long userId) {
        return null;
    }
}

