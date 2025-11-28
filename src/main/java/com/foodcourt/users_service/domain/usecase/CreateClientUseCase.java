package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.EmailAlreadyExistsException;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateClientServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;


public class CreateClientUseCase implements ICreateClientServicePort {

    private final IUserPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;
    private final IUserValidationService validationService;

    public CreateClientUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IUserValidationService validationService) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.validationService = validationService;
    }

    @Override
    public User create(User client) {

        User user = userPersistencePort.getUserByeEmail(client.getEmail());

        if(user != null){
            throw new EmailAlreadyExistsException();
        }
        //format email, phone, identification
        validationService.validate(client);

        client.setRole(Role.CLIENT);
        client.setPassword(passwordEncoderPort.encode(client.getPassword()));
        return userPersistencePort.saveUser(client);
    }
}
