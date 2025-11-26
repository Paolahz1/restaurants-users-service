package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.spi.IUserValidationService;
import com.foodcourt.users_service.domain.service.UserValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserValidationServiceConfiguration {

    @Bean
    public IUserValidationService userValidationService(){
        return new UserValidationService();
    }
}
