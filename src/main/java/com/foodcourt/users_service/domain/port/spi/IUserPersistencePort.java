package com.foodcourt.users_service.domain.port.spi;
import com.foodcourt.users_service.domain.model.Person;
import java.util.Optional;

public interface IUserPersistencePort {

    boolean save (Person person);
    Optional<Person> findById(Long id);

    Optional<Person> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByIdentityDocument(String document);
}