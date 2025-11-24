package com.foodcourt.users_service.domain.port.spi;

import com.foodcourt.users_service.domain.model.Role;

public interface IAuthPersistencePort {

    Role getRoleById(Long idUser);

}
