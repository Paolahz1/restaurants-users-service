package com.foodcourt.users_service.domain.port.spi;
import com.foodcourt.users_service.domain.model.Owner;
import java.util.Optional;

public interface IOwnerPersistencePort {

    void saveOwner(Owner owner);
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findById(Long id);
    boolean existsByEmail(String email);
    boolean existsByIdentityDocument(String document);
}