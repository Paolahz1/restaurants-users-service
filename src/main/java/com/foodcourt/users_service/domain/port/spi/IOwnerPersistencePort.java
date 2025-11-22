package com.foodcourt.users_service.domain.port.spi;
import com.foodcourt.users_service.domain.model.Owner;
import java.util.Optional;

public interface IOwnerPersistencePort {

    void saveOwner(Owner owner);
    Optional<Owner> getByEmail(String email);
    boolean existsByEmail(String email);
}