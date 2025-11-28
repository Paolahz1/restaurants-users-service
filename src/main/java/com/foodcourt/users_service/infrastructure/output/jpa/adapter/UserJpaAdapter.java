package com.foodcourt.users_service.infrastructure.output.jpa.adapter;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserJpaRepository userRepository;
    private final IUserEntityMapper userMapper;


    @Override
    public Role getRoleById(Long idUser) {
        return userRepository.getRoleById(idUser);
    }

    @Override
    public User getUserById(Long idUser) {
        return userRepository.findById(idUser)
                .map(userMapper::toDomain)
                .orElse(null);
    }

    @Override
    public User getUserByeEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDomain)
                .orElse(null);
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toDomain( userRepository.save(userEntity));
    }

}
