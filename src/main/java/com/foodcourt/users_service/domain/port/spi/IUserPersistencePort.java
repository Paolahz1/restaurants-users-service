package com.foodcourt.users_service.domain.port.spi;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;

public interface IUserPersistencePort {

    Role getRoleById(Long idUser);

    User getUserById(Long idUser);

    User getUserByeEmail(String email);

    void saveUser(User user);
}
