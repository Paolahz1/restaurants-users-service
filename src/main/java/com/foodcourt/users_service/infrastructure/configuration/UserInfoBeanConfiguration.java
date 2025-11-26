package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.api.IUserGetInfoServicePort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.usecase.GetUserInfoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserInfoBeanConfiguration {

    private final IUserPersistencePort persistencePort;
    @Bean
    public IUserGetInfoServicePort userGetInfoServicePort(){
        return new GetUserInfoUseCase(persistencePort);
    }
}
