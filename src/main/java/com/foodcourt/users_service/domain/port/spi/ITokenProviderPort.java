package com.foodcourt.users_service.domain.port.spi;

import com.foodcourt.users_service.domain.model.Person;
import jakarta.persistence.Persistence;

public interface ITokenProviderPort {

    String generateToken(Person person);
    Long getUserIdFromToken(String token);
    String getRoleFromToken(String token);

}
