package com.foodcourt.users_service.infrastructure.output.jpa.adapter;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IOwnerEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class OwnerJpaAdapter implements IOwnerPersistencePort {

    private final IUserJpaRepository ownerRepository;
    private final IOwnerEntityMapper entityMapper;

    @Override
    public void saveOwner(Owner owner) {
        UserEntity entity = entityMapper.toEntity(owner);
        ownerRepository.save(entity);
    }

    @Override
    public Optional<Owner> getByEmail(String email) {
        return ownerRepository.findByEmail(email)
                .filter(e -> e.getRole() == Role.OWNER)
                .map(entityMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return ownerRepository.findByEmail(email).isPresent();
    }


}
