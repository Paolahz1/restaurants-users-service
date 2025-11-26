package com.foodcourt.users_service.domain.usecase;

import com.foodcourt.users_service.domain.exception.*;
import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;
import com.foodcourt.users_service.domain.port.api.ICreateOwnerServicePort;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import com.foodcourt.users_service.domain.port.spi.IUserPersistencePort;
import com.foodcourt.users_service.domain.port.spi.IUserValidationService;

import java.time.LocalDate;

public class CreateOwnerUseCase implements ICreateOwnerServicePort {

    private final IUserPersistencePort userPersistencePort;
    private  final IPasswordEncoderPort passwordEncoderPort;
    private final IUserValidationService validationService;

    public CreateOwnerUseCase(
            IUserPersistencePort userPersistencePort,
            IPasswordEncoderPort passwordEncoderPort,
            IUserValidationService validationService) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.validationService = validationService;
    }

    @Override
    public void createOwner(User owner) {

        User user = userPersistencePort.getUserByeEmail(owner.getEmail());
        if(user != null){
            throw new EmailAlreadyExistsException();
        }

        if (owner.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new UnderageOwnerException();
        }

        //format of email, phone, identification
        validationService.validate(owner);

        owner.setRole(Role.OWNER);
        owner.setPassword(passwordEncoderPort.encode(owner.getPassword()));
        userPersistencePort.saveUser(owner);

    }
}
