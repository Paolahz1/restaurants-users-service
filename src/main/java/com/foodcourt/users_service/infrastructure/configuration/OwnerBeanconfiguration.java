package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.api.ICreateOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;
import com.foodcourt.users_service.domain.usecase.CreateOwnerUseCase;
import com.foodcourt.users_service.infrastructure.output.jpa.adapter.PasswordBcryptAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class OwnerBeanconfiguration {

    private final IUserPersistencePort persistencePort;
    private final IUserValidationService validationService;

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(PasswordEncoder encoder){
        return new PasswordBcryptAdapter(encoder);
    }

    @Bean
    public ICreateOwnerServicePort ownerServicePort(
            IPasswordEncoderPort passwordEncoderPort){
       return new CreateOwnerUseCase(persistencePort, passwordEncoderPort, validationService);
    }
}
