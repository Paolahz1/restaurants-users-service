package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.api.ICreateOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IOwnerPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.usecase.CreateOwnerUseCase;
import com.foodcourt.users_service.infrastructure.output.jpa.adapter.OwnerJpaAdapter;
import com.foodcourt.users_service.infrastructure.output.jpa.adapter.PasswordBcryptAdapter;
import com.foodcourt.users_service.infrastructure.output.jpa.mapper.IOwnerEntityMapper;
import com.foodcourt.users_service.infrastructure.output.jpa.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class OwnerBeanconfiguration {

    private final IUserJpaRepository jpaRepository;
    private  final IOwnerEntityMapper entityMapper;


    @Bean
    public IOwnerPersistencePort ownerPersistencePort(){
        return new OwnerJpaAdapter(jpaRepository, entityMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(PasswordEncoder encoder){
        return new PasswordBcryptAdapter(encoder);
    }

    @Bean
    public ICreateOwnerServicePort ownerServicePort(IOwnerPersistencePort persistencePort, IPasswordEncoderPort passwordEncoderPort){
       return new CreateOwnerUseCase(persistencePort, passwordEncoderPort);
    }
}
