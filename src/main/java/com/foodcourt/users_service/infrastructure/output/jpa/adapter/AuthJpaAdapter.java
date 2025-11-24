package com.foodcourt.users_service.infrastructure.output.jpa.adapter;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {

    private final IUserJpaRepository userRepository;

    @Override
    public Role getRoleById(Long idUser) {
        return userRepository.getRoleById(idUser);
    }
}
