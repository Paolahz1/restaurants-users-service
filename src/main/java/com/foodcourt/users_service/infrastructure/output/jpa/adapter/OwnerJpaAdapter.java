package com.foodcourt.users_service.infrastructure.output.jpa.adapter;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IOwnerEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IOwnerJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OwnerJpaAdapter implements IOwnerPersistencePort {

    private final IOwnerJpaRepository ownerRepository;
    private final IOwnerEntityMapper entityMapper;

    @Override
    public void saveOwner(Owner owner) {
        UserEntity entity = entityMapper.toEntity(owner);
        ownerRepository.save(entity);
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerRepository.findByEmail(email)                  // <-- llama al JPA repository
                .filter(e -> e.getRole() == Role.OWNER)  // <-- aplica filtro
                .map(entityMapper::toDomain);                      // <-- mapea a dominio
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByIdentityDocument(String document) {
        return false;
    }
}
