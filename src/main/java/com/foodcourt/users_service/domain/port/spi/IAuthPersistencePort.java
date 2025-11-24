package com.foodcourt.users_service.domain.port.spi;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Person;
import com.foodcourt.users_service.domain.model.Role;

public interface IAuthPersistencePort {

    Role getRoleById(Long idUser);

    Owner getUserById(Long idUser);
}
