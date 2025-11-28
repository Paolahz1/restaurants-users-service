package com.foodcourt.users_service.infrastructure.configuration;

import com.foodcourt.users_service.domain.port.api.ICreateEmployeeServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;
import com.foodcourt.users_service.domain.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmployeeBeanConfiguration {

    private final IUserPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;
    private final IUserValidationService validationService;

     @Bean
    public ICreateEmployeeServicePort createEmployeeServicePort(){
         return new CreateEmployeeUseCase(userPersistencePort,passwordEncoderPort, validationService );
     }
}
