package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.Role;

public interface IAuthServicePort {

    /**
     * Autentica un usuario por email y contraseña.
     * Retorna un JWT si es válido.
     */
    String login(String email, String rawPassword);
    Role getUserRoleById(Long userId);
}
