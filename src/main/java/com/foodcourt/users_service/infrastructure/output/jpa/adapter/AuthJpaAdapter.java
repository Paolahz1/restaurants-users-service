package com.foodcourt.users_service.infrastructure.output.jpa.adapter;

import com.foodcourt.users_service.domain.model.Owner;
import com.foodcourt.users_service.domain.model.Person;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IOwnerEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {

    private final IUserJpaRepository userRepository;
    private final IOwnerEntityMapper mapper;
    private final IUserEntityMapper userMapper;
    @Override
    public Role getRoleById(Long idUser) {
        return userRepository.getRoleById(idUser);
    }

    @Override
    public Owner getUserById(Long idUser) {
        return userRepository.findById(idUser)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public User getUserByeEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain)
                .orElse(null);
    }


}
