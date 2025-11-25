package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;

public interface IAuthServicePort {

    /**
     * Autentica un usuario por email y contraseña.
     * Retorna un JWT si es válido.
     */

    String login(String email, String rawPassword);
    Role getUserRoleById(Long userId);
    Owner getUserById(Long userId);
    User getUserByEmail(String email);
}
