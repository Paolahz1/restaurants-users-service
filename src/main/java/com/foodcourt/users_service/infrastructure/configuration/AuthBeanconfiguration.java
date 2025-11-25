package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.api.IAuthServicePort;

import com.foodcourt.users_service.domain.port.spi.IAuthPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.usecase.AuthUseCase;
import com.foodcourt.users_service.domain.usecase.CreateOwnerUseCase;
import com.foodcourt.users_service.infrastructure.output.jpa.adapter.OwnerJpaAdapter;
import com.foodcourt.users_service.infrastructure.output.jpa.adapter.PasswordBcryptAdapter;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IOwnerEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthBeanconfiguration {

    private final IAuthPersistencePort persistencePort;

    @Bean
    public IAuthServicePort authServicePort( ){
       return new AuthUseCase(persistencePort);
    }
}
